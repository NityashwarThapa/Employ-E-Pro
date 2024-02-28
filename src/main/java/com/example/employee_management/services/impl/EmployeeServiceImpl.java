package com.example.employee_management.services.impl;

import com.example.employee_management.Entity.Employee;
import com.example.employee_management.repo.EmployeeRepository;
import com.example.employee_management.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

}
