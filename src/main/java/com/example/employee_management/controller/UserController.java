package com.example.employee_management.controller;
import com.example.employee_management.Entity.Employee;
import com.example.employee_management.Entity.User;
import com.example.employee_management.pojo.UserPojo;

import com.example.employee_management.repo.UserRepo;
import com.example.employee_management.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
//    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/canteen_mgmt";


    @GetMapping("/index")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "user/create";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "user/login";
        }
        return ("redirect:/user/user_list");
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserPojo());
        return "register";
    }

    @PostMapping("/create")
    public String createUser(@Valid UserPojo userPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/register";
        }
        userService.save(userPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User registered successfully");
        return "redirect:/login";
    }
    @GetMapping("/userList")
    public String getUser(Model model) {
        List<User> users = userService.fetchAll();
        model.addAttribute("userList", users.stream().map(user ->
                        User.builder()
                                .id(user.getId())
//                        .imageBase64(getImageBase64(user.getImage()))
                                .fullName(user.getFullName())
                                .email(user.getEmail())
                                .build()
        ));
//        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);
        return "user/user_list";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", new UserPojo(user));
        return "register";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteMsg", "Row delete successfully");
        return "redirect:/user/admin_account";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

//        private String convertImageToBase64(String filename) {
//        String filePath = System.getProperty("user.dir") + "/canteen_mgmt/" + filename;
//    }
//    public String getImageBase64(String fileName) {
//        String filePath = System.getProperty("user.dir") + "/canteen_mgmt/";
//        File file = new File(filePath + fileName);
//        byte[] bytes = new byte[0];
//        try {
//            bytes = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        String base64 = Base64.getEncoder().encodeToString(bytes);
//        return base64;
//    }


}

