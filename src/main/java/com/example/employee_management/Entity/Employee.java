package com.example.employee_management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    public Integer salary;

    public Employee(String firstName, String lastName, String email, String phone_number, String department, Integer salary) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.department = department;
        this.salary = salary;

    }


}
