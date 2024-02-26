package com.example.employee_management;

import com.example.employee_management.Entity.Employee;
import com.example.employee_management.repo.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {EmployeeManagementApplication.class})
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveEmployeeTest(){
        Employee employee= Employee.builder()
                .firstName("Anusha")
                .lastName("Anusha")
                .email("anusha@gmail.com")
                .phone_number("980799999")
                .department("account")
                .salary(12345)
                .build();
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getEmployeeTest(){
        Employee employeeCreated=employeeRepository.findById(1).get();
        Assertions.assertThat(employeeCreated.getId()).isEqualTo(1);
    }
    @Test
    @Order(3)
    public void getListOfEmployeeTest(){
        List<Employee> Employees=employeeRepository.findAll();
        Assertions.assertThat(Employees.size()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    @Rollback(value=false)
    public void updateEmployeeTest(){
        Employee employee=employeeRepository.findById(1).get();
        employee.setFirstName("ANU");
        Employee employeeUpdated=employeeRepository.save(employee);
        Assertions.assertThat(employeeUpdated.getFirstName()).isEqualTo("ANUSHA");
    }
    @Test
    @Order(5)
    @Rollback(value=false)
    public void deleteUserTest(){
        Employee employee=employeeRepository.findById(1).get();
        employeeRepository.delete(employee);
        Employee employee1=null;
        Optional<Employee> optionalEmployee=employeeRepository.findEmployeeByFirstName("ANUSHA");
        if(optionalEmployee.isPresent()){
            employee1=optionalEmployee.get();
        }
        Assertions.assertThat(employee1).isNull();
    }

}