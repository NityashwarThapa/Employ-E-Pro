package com.example.employee_management.controller;
import com.example.employee_management.Entity.Task;
import com.example.employee_management.repo.UserRepo;
import com.example.employee_management.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.attoparser.dom.INestableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class TaskController {
    @Autowired
    private TaskService taskService;
    private final UserRepo userRepo;

    @GetMapping("/task")
    public String getTaskPage(Model model) {
        model.addAttribute("taskList", taskService.getAllTask());
        return "task";
    }

//    @GetMapping("/tasklist")
//    public String getTask(Model model) {
//        List<Task> tasks = taskService.getAllTask();
//        model.addAttribute("task", tasks.stream().map(task ->
//                        Task.builder()
//                                .id(task.getId())
////                        .imageBase64(getImageBase64(user.getImage()))
//                                .employee_name(task.getEmployee_name())
//                                .task_assigned(task.getTask_assigned())
//                                .assigned_date(task.getAssigned_date())
//                                .build()
//        ));
////        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);
//        return "task";
//    }
    @GetMapping("/task/new")
    public String createTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addTask";
    }

    @PostMapping("/task")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/task";
    }

    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable Integer id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "editTask";
    }

    @PostMapping("/task/{id}")
    public String updateTask(@PathVariable Integer id,
                                 @ModelAttribute("task") Task task,
                                 Model model) {

        // get task from database by id
        Task existingTask = taskService.getTaskById(id);
        existingTask.setId(id);
        existingTask.setEmployee_name(task.getEmployee_name());
        existingTask.setTask_assigned(task.getTask_assigned());
        existingTask.setAssigned_date(task.getAssigned_date());

        // save updated task object
        taskService.updateTask(existingTask);
        return "redirect:/task";
    }
    @GetMapping("/task/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
        return "redirect:/task";
    }
}
