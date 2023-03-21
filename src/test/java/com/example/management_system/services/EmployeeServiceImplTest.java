package com.example.management_system.services;

import com.example.management_system.dao.EmployeeDao;
import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;
    Employee employee1 = new Employee(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
    Employee employee2 = new Employee(2,"Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology");


    @BeforeEach
    void setUp(){
        this.employeeService = new EmployeeServiceImpl(this.employeeDao);
    }

    @Test
    void getAll() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology"));
        employeeList.add(new Employee(2,"Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology"));

        when(employeeDao.findAll()).thenReturn(employeeList);
        assertEquals(2, employeeService.getAll().size());
    }

    @Test
    void getAllByPageSort() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology"));
        employeeList.add(new Employee(2,"Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology"));
        Pageable p = PageRequest.of(1, 1, Sort.by("age").descending());
        Page<Employee> pagedTasks = new PageImpl(employeeList);
        when(employeeDao.findAll(p)).thenReturn(pagedTasks);
        assertEquals(2, employeeService.getAllByPageSort(1,1,"age", "desc").size());


    }

    @Test
    void getById() {
        Mockito.when(employeeDao.findById(1)).thenReturn(employee1);
        String expectedResult = "Ankit";
        assertEquals(expectedResult, employeeService.getById(1).getName());
    }

    @Test
    void getByNameAndAge() {
        Mockito.when(employeeDao.findByNameAndAge("Ankit",21)).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByNameAndAge("Ankit", 21).size());

    }

    @Test
    void getBySalary() {
        Mockito.when(employeeDao.findBySalary(50000)).thenReturn(Stream.of(employee2).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getBySalary(50000).size());
    }

    @Test
    void getByPosition() {
        Mockito.when(employeeDao.findByPosition("Java Developer")).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByPosition("Java Developer").size());
    }

    @Test
    void getByPositionAndDepartment() {
        Mockito.when(employeeDao.findByPositionAndDepartment("Java Developer","Technology")).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByPositionAndDepartment("Java Developer","Technology").size());
    }

    @Test
    void add() {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        Mockito.when(employeeDao.save(any())).thenReturn(employee1);
        assertEquals(employee1.getName(), employeeService.add(employeeRequestDto).getName());
    }

    @Test
    void update() {
        Mockito.when(employeeDao.save(employee1)).thenReturn(employee1);
        String actualResult = "Employee Details Updated";
        assertEquals(actualResult, employeeService.update(employee1));
    }

    @Test
    void deleteById() {
        doNothing().when(employeeDao).deleteById(any());
        when(employeeDao.findById(anyLong())).thenReturn(employee1);
        employeeService.deleteById(1L);
        verify(employeeDao, times(1)).deleteById(1L);
    }

    @Test
    void deleteAll() {
        doNothing().when(employeeDao).deleteAll();
        employeeService.deleteAll();
        verify(employeeDao, times(1)).deleteAll();
    }
}