package com.example.management_system.services;

import com.example.management_system.dao.EmployeeDao;
import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    List<Employee> employeeList = new ArrayList<Employee>();
    List<Employee> emptyList = new ArrayList<Employee>();
    Employee employee1;
    Employee employee2;
    EmployeeRequestDto employeeRequestDto;

    @BeforeEach
    void setUp(){
        this.employeeService = new EmployeeServiceImpl(this.employeeDao);
        employee1 = new Employee(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        employee2 = new Employee(2,"Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology");
        employeeRequestDto = new EmployeeRequestDto(1,"Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @Test
    void getAll_IfEmployeesPresent_Success() {
        when(employeeDao.findAll()).thenReturn(employeeList);
        assertEquals(2, employeeService.getAll().size());
    }

    @Test
    void getAll_IfEmployeesNotPresent_EmptyList() {
        when(employeeDao.findAll()).thenReturn(emptyList);
        assertEquals(emptyList, employeeService.getAll());
    }

    @Test
    void getAllByPageSort_IfPresent_Success() {
        Pageable p = PageRequest.of(1, 1, Sort.by("age").descending());
        Page<Employee> pagedTasks = new PageImpl(employeeList);
        when(employeeDao.findAll(p)).thenReturn(pagedTasks);
        assertEquals(2, employeeService.getAllByPageSort(1,1,"age", "desc").size());
    }

    @Test
    void getAllByPageSort_IfNotPresent_ExceptionThrown() {
        Pageable p = PageRequest.of(1, 1, Sort.by("age").descending());
        Page<Employee> pagedTasks = new PageImpl(emptyList);
        when(employeeDao.findAll(p)).thenReturn(pagedTasks);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getAllByPageSort(1,1,"age", "desc"));
    }

    @Test
    void getById_IfPresent_Success() {
        when(employeeDao.findById(1)).thenReturn(employee1);
        String expectedResult = "Ankit";
        assertEquals(expectedResult, employeeService.getById(1).getName());
    }

    @Test
    void getById_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findById(anyLong())).thenReturn(null);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getById(76L));
    }

    @Test
    void getByNameAndAge_IfPresent_Success() {
        when(employeeDao.findByNameAndAge("Ankit",21)).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByNameAndAge("Ankit", 21).size());

    }

    @Test
    void getByNameAndAge_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findByNameAndAge(anyString(),anyInt())).thenReturn(emptyList);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getByNameAndAge(anyString(), anyInt()));

    }

    @Test
    void getBySalary_IfPresent_Success() {
        when(employeeDao.findBySalary(50000)).thenReturn(Stream.of(employee2).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getBySalary(50000).size());
    }

    @Test
    void getBySalary_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findBySalary(anyLong())).thenReturn(emptyList);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getBySalary(anyLong()));
    }

    @Test
    void getByPosition_IfPresent_Success() {
        when(employeeDao.findByPosition("Java Developer")).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByPosition("Java Developer").size());
    }

    @Test
    void getByPosition_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findByPosition(anyString())).thenReturn(emptyList);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getByPosition(anyString()));
    }

    @Test
    void getByPositionAndDepartment_IfPresent_Success() {
        when(employeeDao.findByPositionAndDepartment("Java Developer","Technology")).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
        int expectedResult = 1;
        assertEquals(expectedResult, employeeService.getByPositionAndDepartment("Java Developer","Technology").size());
    }

    @Test
    void getByPositionAndDepartment_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findByPositionAndDepartment(anyString(), anyString())).thenReturn(emptyList);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.getByPositionAndDepartment(anyString(), anyString()));
    }

    @Test
    void add_IfNotPresent_Success() {
        when(employeeDao.save(any())).thenReturn(employee1);
        assertEquals(employee1.getName(), employeeService.add(employeeRequestDto).getName());
    }

    @Test
    void add_IfPresent_ExceptionThrown() {
        Mockito.when(employeeDao.findByEmail(any())).thenReturn(employee1);
        assertThrowsExactly(ResourceAlreadyExistException.class, () -> employeeService.add(employeeRequestDto));
    }

    @Test
    void update_IfPresent_Success() {
        when(employeeDao.findById(anyLong())).thenReturn(employee1);
        String actualResult = "Employee Details Updated";
        assertEquals(actualResult, employeeService.update(employee1));
    }

    @Test
    void update_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findById(anyLong())).thenReturn(null);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.update(employee2));
    }

    @Test
    void deleteById_IfPresent_Success() {
        doNothing().when(employeeDao).deleteById(any());
        when(employeeDao.findById(anyLong())).thenReturn(employee1);
        employeeService.deleteById(1L);
        verify(employeeDao, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_IfNotPresent_ExceptionThrown() {
        when(employeeDao.findById(anyLong())).thenReturn(null);
        assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.deleteById(anyLong()));
    }

    @Test
    void deleteAll_IfPresent_Success() {
        doNothing().when(employeeDao).deleteAll();
        employeeService.deleteAll();
        verify(employeeDao, times(1)).deleteAll();
    }
}