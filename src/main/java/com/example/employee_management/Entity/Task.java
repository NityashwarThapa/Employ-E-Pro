package com.example.employee_management.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "employee_name")
    public String employee_name;

    @Column(name = "task_assigned")
    public String task_assigned;

    @Column(name = "assigned_date")
    public String assigned_date;

    public Task(String employee_name,String task_assigned, String assigned_date) {
        super();
        this.employee_name = employee_name;
        this.task_assigned = task_assigned;
        this.assigned_date = assigned_date;

    }
}