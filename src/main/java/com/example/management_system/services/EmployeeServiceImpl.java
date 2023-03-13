package com.example.management_system.services;

import com.example.management_system.dao.EmployeeDao;
import com.example.management_system.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> getAllByPageSort(int pageNumber, int pageSize, String sortBy, String sortDirection){

        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Employee> employeePage = employeeDao.findAll(p);
        List<Employee> allEmployee = employeePage.getContent();
        return allEmployee;
    }

    @Override
    public Optional<Employee> getById(long id) {
        return employeeDao.findById(id);
    }

    @Override
    public Employee add(Employee employee) {
        employeeDao.save(employee);
        return employee;
    }

    @Override
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "Employee Details Updated";
    }

    @Override
    public String deleteById(long id) {
        employeeDao.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public String deleteAll() {
        employeeDao.deleteAll();
        return "All records deleted";
    }
}
