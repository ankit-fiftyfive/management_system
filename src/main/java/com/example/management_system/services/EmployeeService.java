package com.example.management_system.services;

import com.example.management_system.dto.EmployeeDto;
import com.example.management_system.dto.EmployeeSubDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeSubDto> getAll();

    public List<EmployeeSubDto> getAllByPageSort(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public EmployeeSubDto getById(long id);

    public List<EmployeeSubDto> getByNameAndAge(String name, int age);

    public List<EmployeeSubDto> getBySalary(long salary);

    public List<EmployeeSubDto> getByPosition(String position);

    public List<EmployeeSubDto> getByPositionAndDepartment(String position, String department);

    public Employee add(EmployeeDto employeeDto) throws ResourceAlreadyExistException;

    public String update(Employee employee);

    public String deleteById(long id);

    public String deleteAll();

}
