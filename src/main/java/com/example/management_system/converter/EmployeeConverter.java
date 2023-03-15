package com.example.management_system.converter;

import com.example.management_system.dto.EmployeeDto;
import com.example.management_system.dto.EmployeeSubDto;
import com.example.management_system.entities.Employee;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class EmployeeConverter {

    public Employee dtoToEntity(EmployeeDto employeeDto){

        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setGender(employeeDto.getGender());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setEmail(employeeDto.getEmail());
        employee.setContactNumber(employeeDto.getContactNumber());
        employee.setLocation(employeeDto.getLocation());
        employee.setCompany(employeeDto.getCompany());
        employee.setPincode(employeeDto.getPincode());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setStreetAddress(employeeDto.getStreetAddress());

        return employee;

    }

    public List<Employee> dtoToEntity(List<EmployeeDto> employeeDtos){
        return employeeDtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

    public EmployeeSubDto entityToDto(Employee employee){

        EmployeeSubDto employeeSubDto = new EmployeeSubDto();
        employeeSubDto.setId(employee.getId());
        employeeSubDto.setName(employee.getName());
        employeeSubDto.setGender(employee.getGender());
        employeeSubDto.setEmail(employee.getEmail());
        employeeSubDto.setContactNumber(employee.getContactNumber());
        employeeSubDto.setPosition(employee.getPosition());
        employeeSubDto.setLocation(employee.getLocation());
        employeeSubDto.setCompany(employee.getCompany());
        employeeSubDto.setDepartment(employeeSubDto.getDepartment());

        return employeeSubDto;

    }

    public List<EmployeeSubDto> entityToDto(List<Employee> employees){
        return employees.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

}
