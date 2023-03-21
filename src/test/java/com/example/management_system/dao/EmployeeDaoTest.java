package com.example.management_system.dao;

import com.example.management_system.entities.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    Employee employee1, employee2;

    @BeforeEach
    void setUp(){
        System.out.println("Starting Up");
        employee1 = new Employee(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        employeeDao.save(employee1);
        employee2 = new Employee(2,"Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology");
        employeeDao.save(employee2);
    }

    @Test
    void findById() {
        Employee responseData = employeeDao.findById(1);
        String actualResult = responseData.getEmail();
        assertThat(actualResult).isEqualTo(employee1.getEmail());
    }

    @Test
    void findByNameAndAge() {
        List<Employee> responseData = employeeDao.findByNameAndAge("Ashu",22);
        String actualResult = responseData.get(0).getEmail();
        assertThat(actualResult).isEqualTo(employee2.getEmail());
    }

    @Test
    void findBySalary() {
        List<Employee> responseData = employeeDao.findBySalary(55000);
        String actualResult = responseData.get(0).getEmail();
        assertThat(actualResult).isEqualTo(employee1.getEmail());
    }

    @Test
    void findByEmail() {
        Employee responseData = employeeDao.findByEmail("ashu@gmail.com");
        String actualResult = responseData.getName();
        assertThat(actualResult).isEqualTo(employee1.getName());
    }

    @Test
    void findByPosition() {
        List<Employee> responseData = employeeDao.findByPosition("Mobile Developer");
        String actualResult = responseData.get(0).getEmail();
        assertThat(actualResult).isEqualTo(employee2.getEmail());
    }

    @Test
    void findByPositionAndDepartment() {
        List<Employee> responseData = employeeDao.findByPositionAndDepartment("Java Developer", "Technology");
        String actualResult = responseData.get(0).getEmail();
        assertThat(actualResult).isEqualTo(employee1.getEmail());
    }


}