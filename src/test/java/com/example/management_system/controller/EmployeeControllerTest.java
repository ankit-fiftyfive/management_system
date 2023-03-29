package com.example.management_system.controller;

import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.dto.EmployeeResponseDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
import com.example.management_system.service.EmployeeService;
import com.example.management_system.util.EmployeeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    List<Employee> employeeList = new ArrayList<>();
    Employee employee1;
    Employee employee2;

    @BeforeEach
    public void SetUp()
    {
        employee1 = new Employee(1, "Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        employee2 = new Employee(2, "Ashu", 22, "Male", "Mobile Developer", 50000, "Delhi", 998375838, "ashu@gmail.com", "delhi", 302030, "fifty six", "Technology");
    }

    @Test
    void getAll_IfPresent_Success() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getAll()).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_IfNotPresent_ExceptionThrown() throws Exception {
        List<EmployeeResponseDto> employeeResponseDto = new ArrayList<>();
        when(employeeService.getAll()).thenReturn(employeeResponseDto);
        mockMvc.perform(get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getAllByPageSort_IfPresent_Success() throws Exception {
        employeeList.add(employee1);
        employeeList.add(employee2);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getAllByPageSort(1, 2, "age", "dsc")).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees/page-sort")
                        .param("pageNumber", "1")
                        .param("pageSize","2")
                        .param("sortBy", "age")
                        .param("sortDirection","dsc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ankit"));
    }

    @Test
    void getAllByPageSort_IfNotPresent_SendEmptyList() throws Exception {
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getAllByPageSort(100, 29, "age", "dsc")).thenThrow(new ResourceNotFoundException("Employees"));
        mockMvc.perform(get("/employees/page-sort")
                        .param("pageNumber", "100")
                        .param("pageSize","29")
                        .param("sortBy", "age")
                        .param("sortDirection","dsc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_IfPresent_Success() throws Exception {
        EmployeeResponseDto employeeResponseDto = EmployeeConverter.entityToDto(employee1);
        when(employeeService.getById(1L)).thenReturn(employeeResponseDto);
        mockMvc.perform(get("/employees/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void getById_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.getById(anyLong())).thenThrow(new ResourceNotFoundException("Employee"));
        mockMvc.perform(get("/employees/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    void getByNameAndAge_IfPresent_Success() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getByNameAndAge("Ankit", 21)).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees/name-and-age")
                        .param("name", "Ankit")
                        .param("age","21")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ankit"));
    }

    @Test
    void getByNameAndAge_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.getByNameAndAge(anyString(), anyInt())).thenThrow(new ResourceNotFoundException("Employee"));
        mockMvc.perform(get("/employees/name-and-age")
                        .param("name", "Ashu")
                        .param("age","21")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBySalary_IfPresent_Success() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getBySalary(55000)).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees/salary")
                        .param("salary", "55000")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ankit"));
    }

    @Test
    void getBySalary_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.getBySalary(anyLong())).thenThrow(new ResourceNotFoundException("Employee"));
        mockMvc.perform(get("/employees/salary")
                        .param("salary", "55000")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByPosition_IfPresent_Success() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee2);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getByPosition("Mobile Developer")).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees/position")
                        .param("position", "Mobile Developer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ashu"));
    }

    @Test
    void getByPosition_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.getByPosition(anyString())).thenThrow(new ResourceNotFoundException("Employees"));
        mockMvc.perform(get("/employees/position")
                        .param("position", "Mobile Developer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByPositionAndDepartment_IfPresent_Success() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        List<EmployeeResponseDto> employeeResponseDtoList = EmployeeConverter.entityToDto(employeeList);
        when(employeeService.getByPositionAndDepartment("Java Developer", "Technology")).thenReturn(employeeResponseDtoList);
        mockMvc.perform(get("/employees/position-and-department")
                        .param("position", "Java Developer")
                        .param("department","Technology")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ankit"));
    }

    @Test
    void getByPositionAndDepartment_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.getByPositionAndDepartment(anyString(),anyString())).thenThrow(new ResourceNotFoundException("Employees"));
        mockMvc.perform(get("/employees/position-and-department")
                        .param("position", "Java Developer")
                        .param("department","Technology")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void add_IfNotPresent_Success() throws Exception {
        EmployeeRequestDto employeeRequestDto1 = new EmployeeRequestDto(1, "Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        when(employeeService.add(any())).thenReturn(employee1);
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequestDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ankit"));
    }

    @Test
    void add_IfPresent_ExceptionThrown() throws Exception {
        EmployeeRequestDto employeeRequestDto1 = new EmployeeRequestDto(1, "Ankit", 21, "Male", "Java Developer", 55000, "Jaipur", 938372838, "ankit@gmail.com", "jaipur", 302010, "fifty five", "Technology");
        when(employeeService.add(any())).thenThrow(new ResourceAlreadyExistException("Employee"));
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequestDto1)))
                .andExpect(status().isConflict());
    }

    @Test
    void update_IfPresent_Success() throws Exception {
        when(employeeService.update(any())).thenReturn("Updated Successfully");
        mockMvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Updated Successfully"));
    }
    @Test
    void update_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.update(any())).thenThrow(new ResourceNotFoundException("Employee"));
        mockMvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteById_IfPresent_Success() throws Exception {
        when(employeeService.deleteById(1)).thenReturn("Deleted Successfully");
        mockMvc.perform(delete("/employees/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Deleted Successfully"));
    }

    @Test
    void deleteById_IfNotPresent_ExceptionThrown() throws Exception {
        when(employeeService.deleteById(anyLong())).thenThrow(new ResourceNotFoundException("employees"));
        mockMvc.perform(delete("/employees/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAll_IfPresent_Success() throws Exception {
        when(employeeService.deleteAll()).thenReturn("All records deleted successfully");
        mockMvc.perform(delete("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("All records deleted successfully"));
    }

}