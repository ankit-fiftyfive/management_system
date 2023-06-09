package com.example.management_system.controller;

import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.dto.EmployeeResponseDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
import com.example.management_system.service.EmployeeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponseDto> getAll(){
        return this.employeeService.getAll();
    }

    @GetMapping("/page-sort")
    public List<EmployeeResponseDto> getAllByPageSort(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                      @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection){
        return this.employeeService.getAllByPageSort(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(this.employeeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/name-and-age")
    public List<EmployeeResponseDto> getByNameAndAge(@RequestParam String name, @RequestParam int age){
        return this.employeeService.getByNameAndAge(name, age);
    }

    @GetMapping("/salary")
    @RolesAllowed("user")
    public List<EmployeeResponseDto> getBySalary(@RequestParam long salary){
        return this.employeeService.getBySalary(salary);
    }

    // JPQL
    @GetMapping("/position")
    public List<EmployeeResponseDto> getByPosition(@RequestParam String position){
        return this.employeeService.getByPosition(position);
    }

    @GetMapping("/position-and-department")
    public List<EmployeeResponseDto> getByPositionAndDepartment(@RequestParam String position, @RequestParam String department){
        return this.employeeService.getByPositionAndDepartment(position, department);
    }

    @PostMapping
    public Employee add(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) throws ResourceAlreadyExistException {
        return this.employeeService.add(employeeRequestDto);
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
