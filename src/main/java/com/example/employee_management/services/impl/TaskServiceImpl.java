package com.example.employee_management.services.impl;
import com.example.employee_management.Entity.Task;
import com.example.employee_management.repo.TaskRepo;
import com.example.employee_management.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Override
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void deleteTaskById(Integer id) {
        taskRepo.deleteById(Math.toIntExact(id));
    }
}
