package com.example.management_system.util;

import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.dto.EmployeeResponseDto;
import com.example.management_system.entities.Employee;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class EmployeeConverter {

    public static Employee dtoToEntity(EmployeeRequestDto employeeRequestDto){

        Employee employee = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setAge(employeeRequestDto.getAge());
        employee.setGender(employeeRequestDto.getGender());
        employee.setPosition(employeeRequestDto.getPosition());
        employee.setSalary(employeeRequestDto.getSalary());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setContact_number(employeeRequestDto.getContact_number());
        employee.setLocation(employeeRequestDto.getLocation());
        employee.setCompany(employeeRequestDto.getCompany());
        employee.setPincode(employeeRequestDto.getPincode());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setStreet_address(employeeRequestDto.getStreet_address());

        return employee;

    }

    public static List<Employee> dtoToEntity(List<EmployeeRequestDto> employeeRequestDtos){
        return employeeRequestDtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

    public static EmployeeResponseDto entityToDto(Employee employee){

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setName(employee.getName());
        employeeResponseDto.setGender(employee.getGender());
        employeeResponseDto.setEmail(employee.getEmail());
        employeeResponseDto.setContactNumber(employee.getContact_number());
        employeeResponseDto.setPosition(employee.getPosition());
        employeeResponseDto.setLocation(employee.getLocation());
        employeeResponseDto.setCompany(employee.getCompany());
        employeeResponseDto.setDepartment(employee.getDepartment());

        return employeeResponseDto;

    }

    public static List<EmployeeResponseDto> entityToDto(List<Employee> employees){
        return employees.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

}
