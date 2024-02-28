package com.example.employee_management.services;

import com.example.employee_management.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Integer id);

}
