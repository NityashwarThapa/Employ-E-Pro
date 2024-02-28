package com.example.employee_management.services.impl;

import com.example.employee_management.Entity.User;
import com.example.employee_management.config.PasswordEncoderUtil;
import com.example.employee_management.pojo.UserPojo;
import com.example.employee_management.repo.UserRepo;
import com.example.employee_management.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
//
//    UserServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }

    @Override
    public UserPojo save(UserPojo userPojo) throws IOException {
        User user;
        if (userPojo.getId() != null) {
            user = userRepo.findById(userPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new User();
        }
        user.setEmail(userPojo.getEmail());
        user.setFullName(userPojo.getFullname());
//        user.setMobileNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));

//        if(userPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            System.out.println(UPLOAD_DIRECTORY);
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getImage().getOriginalFilename());
//            fileNames.append(userPojo.getImage().getOriginalFilename());
//            Files.write(fileNameAndPath, userPojo.getImage().getBytes());

//            user.setImage(userPojo.getImage().getOriginalFilename());
        userRepo.save(user);
        return new UserPojo(user);
    }
    public List<User> fetchAll() {
        return userRepo.findAll();
    }
    public User getUserById(Integer id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }
    public void deleteById(Integer id){
        userRepo.deleteById(id);
    }
//    @Override
//    public void save(User user) {
//    }
    @Override
    public void login(String username, String password) {
    }
//    @Override
//    public User findByUsername(String username) {
//        return null;
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return null;
//    }

    @Override
    public User findById(long id) {
        return null;
    }

}
