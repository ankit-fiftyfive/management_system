package com.example.management_system.service;

import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.dto.EmployeeResponseDto;
import com.example.management_system.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeResponseDto> getAll();

    public List<EmployeeResponseDto> getAllByPageSort(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public EmployeeResponseDto getById(long id);

    public List<EmployeeResponseDto> getByNameAndAge(String name, int age);

    public List<EmployeeResponseDto> getBySalary(long salary);

    public List<EmployeeResponseDto> getByPosition(String position);

    public List<EmployeeResponseDto> getByPositionAndDepartment(String position, String department);

    public Employee add(EmployeeRequestDto employeeRequestDto);

    public String update(Employee employee);

    public String deleteById(long id);

    public String deleteAll();

}
