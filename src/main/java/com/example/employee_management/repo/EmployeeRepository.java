package com.example.employee_management.repo;

import com.example.employee_management.Entity.Employee;
import com.example.employee_management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * from employee where email=?1", nativeQuery = true)
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findEmployeeByFirstName(String firstName);
}
