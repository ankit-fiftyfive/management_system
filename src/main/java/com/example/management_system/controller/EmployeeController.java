package com.example.management_system.controller;

import com.example.management_system.entities.Employee;
import com.example.management_system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll(){
        return this.employeeService.getAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable long id){
        return this.employeeService.getById(id);
    }

    @PostMapping("/employees")
    public Employee add(@RequestBody Employee employee){
        return this.employeeService.add(employee);
    }

    @PutMapping("/employees")
    public String update(@RequestBody Employee employee){
        return this.employeeService.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteById(@PathVariable long id){
        return this.employeeService.deleteById(id);
    }

    @DeleteMapping("/employees")
    public String deleteAll(){
        return this.employeeService.deleteAll();
    }

}
