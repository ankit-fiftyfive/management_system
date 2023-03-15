package com.example.management_system.services;

import com.example.management_system.converter.EmployeeConverter;
import com.example.management_system.dao.EmployeeDao;
import com.example.management_system.dto.EmployeeDto;
import com.example.management_system.dto.EmployeeSubDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    private EmployeeConverter employeeConverter = new EmployeeConverter();

    @Override
    public List<EmployeeSubDto> getAll() {
        if(employeeDao.findAll().isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(employeeDao.findAll());
    }

    @Override
    public List<EmployeeSubDto> getAllByPageSort(int pageNumber, int pageSize, String sortBy, String sortDirection){

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
        if(allEmployee.isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(allEmployee);
    }

    @Override
    public EmployeeSubDto getById(long id){
        if(employeeDao.findById(id) == null){
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        return employeeConverter.entityToDto(employeeDao.findById(id));
    }
    
    @Override
    public List<EmployeeSubDto> getByNameAndAge(String name, int age){
        if(employeeDao.findByNameAndAge(name, age).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(employeeDao.findByNameAndAge(name, age));
    }

    public List<EmployeeSubDto> getBySalary(long salary){
        if(employeeDao.findBySalary(salary).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(employeeDao.findBySalary(salary));
    }

    @Override
    public List<EmployeeSubDto> getByPosition(String position){
        if(employeeDao.findByPosition(position).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(employeeDao.findByPosition(position));
    }

    @Override
    public List<EmployeeSubDto> getByPositionAndDepartment(String position, String department){
        if(employeeDao.findByPositionAndDepartment(position, department).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return employeeConverter.entityToDto(employeeDao.findByPositionAndDepartment(position, department));
    }
    @Override
    public Employee add(EmployeeDto employeeDto) throws ResourceAlreadyExistException {

        if(employeeDao.findByEmail(employeeDto.getEmail()) != null){
            throw new ResourceAlreadyExistException("Employee", "email", employeeDto.getEmail());
        }
        Employee employee = employeeConverter.dtoToEntity(employeeDto);
        employeeDao.save(employee);
        return employee;
    }

    @Override
    public String update(Employee employee){
        employeeDao.save(employee);
        return "Employee Details Updated";
    }

    @Override
    public String deleteById(long id) {
        if(employeeDao.findById(id) == null){
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        employeeDao.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public String deleteAll() {
        employeeDao.deleteAll();
        return "All records deleted";
    }



}
