package com.example.management_system.controller;

import com.example.management_system.entities.Employee;
import com.example.management_system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll(){
        return this.employeeService.getAll();
    }

    @GetMapping("/page-sort")
    public List<Employee> getAllByPageSort(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
                                              @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                              @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection){
        return this.employeeService.getAllByPageSort(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable long id){
        return this.employeeService.getById(id);
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee){
        return this.employeeService.add(employee);
    }

    @PutMapping
    public String update(@RequestBody Employee employee){
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
