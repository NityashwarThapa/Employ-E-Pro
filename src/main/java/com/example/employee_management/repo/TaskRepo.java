package com.example.employee_management.repo;

import com.example.employee_management.Entity.Task;
import com.example.employee_management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}
