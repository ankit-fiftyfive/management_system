package com.example.management_system.controller;

import com.example.management_system.dto.EmployeeDto;
import com.example.management_system.dto.EmployeeSubDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
import com.example.management_system.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeSubDto> getAll(){
        return this.employeeService.getAll();
    }

    @GetMapping("/page-sort")
    public List<EmployeeSubDto> getAllByPageSort(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
                                              @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                              @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection){
        return this.employeeService.getAllByPageSort(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSubDto> getById(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(this.employeeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/name-and-age")
    public List<EmployeeSubDto> getByNameAndAge(@RequestParam String name, @RequestParam int age){
        return this.employeeService.getByNameAndAge(name, age);
    }

    @GetMapping("/salary")
    public List<EmployeeSubDto> getBySalary(@RequestParam long salary){
        return this.employeeService.getBySalary(salary);
    }

    // JPQL
    @GetMapping("/position")
    public List<EmployeeSubDto> getByPosition(@RequestParam String position){
        return this.employeeService.getByPosition(position);
    }

    @GetMapping("/position-and-department")
    public List<EmployeeSubDto> getByPositionAndDepartment(@RequestParam String position, @RequestParam String department){
        return this.employeeService.getByPositionAndDepartment(position, department);
    }

    @PostMapping
    public Employee add(@Valid @RequestBody EmployeeDto employeeDto) throws ResourceAlreadyExistException {
        return this.employeeService.add(employeeDto);
    }

    @PutMapping
    public String update(@Valid @RequestBody Employee employee){
        return this.employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        return this.employeeService.deleteById(id);
    }

    @DeleteMapping
    public String deleteAll(){
        return this.employeeService.deleteAll();
    }



}
