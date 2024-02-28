package com.example.employee_management.controller;

import com.example.employee_management.Entity.Employee;
import com.example.employee_management.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
    @GetMapping("/records")
    public String showEmployeeRecords(Model model) {
        // Add necessary model attributes and logic here
        return "records"; // Return the name of the Thymeleaf template
    }

    // handler method to handle list employee and return mode and view
    @GetMapping("/employee")
    public String listEmployee(Model model) {
        model.addAttribute("employee", employeeService.getAllEmployee());
        return "employee";
    }

    @GetMapping("/employee/new")
    public String createEmployeeForm(Model model) {
        // create employee object to hold employee form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/view/{id}")
    public String viewProfile(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee_profile";
    }

    //update ko button ma haleko
    @GetMapping("/employee/edit/{id}")
    public String editEmployeeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    //update page ko form action ho
    @PostMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Integer id,
                                @ModelAttribute("employee") Employee employee,
                                Model model) {

        // get employee from database by id
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone_number(employee.getPhone_number());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        // save updated employee object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employee";
    }

//    @GetMapping("/employees")
//    public String getEmployee(Model model) {
//        List<Employee> employees = employeeService.getAllEmployee();
//        model.addAttribute("employeeList", employees.stream().map(employee ->
//                        Employee.builder()
//                                .id(employee.getId())
////                        .imageBase64(getImageBase64(user.getImage()))
//                                .firstName(employee.getFirstName())
//                                .lastName(employee.getLastName())
//                                .phone_number(employee.getPhone_number())
//                                .department(employee.getDepartment())
//                                .salary(employee.getSalary())
//                                .build()
//        ));
////        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);
//        return "task";
//    }

    // handler method to handle delete employee request
    @GetMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employee";
    }

}
