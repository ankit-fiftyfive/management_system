package com.example.management_system.services;

import com.example.management_system.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAll();

    public Employee getById(long id);

    public Employee add(Employee employee);

    public String update(Employee employee);

    public String deleteById(long id);

    public String deleteAll();
}
