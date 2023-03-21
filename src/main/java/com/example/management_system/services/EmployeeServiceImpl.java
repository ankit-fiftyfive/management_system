package com.example.management_system.services;

import com.example.management_system.dao.EmployeeDao;
import com.example.management_system.dto.EmployeeRequestDto;
import com.example.management_system.dto.EmployeeResponseDto;
import com.example.management_system.entities.Employee;
import com.example.management_system.exception.ResourceAlreadyExistException;
import com.example.management_system.exception.ResourceNotFoundException;
import com.example.management_system.util.EmployeeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    public List<EmployeeResponseDto> getAll() {
        if(employeeDao.findAll().isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return EmployeeConverter.entityToDto(employeeDao.findAll());
    }

    @Override
    public List<EmployeeResponseDto> getAllByPageSort(int pageNumber, int pageSize, String sortBy, String sortDirection){

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
        return EmployeeConverter.entityToDto(allEmployee);
    }

    @Override
    public EmployeeResponseDto getById(long id){
        if(employeeDao.findById(id) == null){
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        return EmployeeConverter.entityToDto(employeeDao.findById(id));
    }
    
    @Override
    public List<EmployeeResponseDto> getByNameAndAge(String name, int age){
        if(employeeDao.findByNameAndAge(name, age).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return EmployeeConverter.entityToDto(employeeDao.findByNameAndAge(name, age));
    }

    @Override
    public List<EmployeeResponseDto> getBySalary(long salary){
        if(employeeDao.findBySalary(salary).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return EmployeeConverter.entityToDto(employeeDao.findBySalary(salary));
    }

    @Override
    public List<EmployeeResponseDto> getByPosition(String position){
        if(employeeDao.findByPosition(position).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return EmployeeConverter.entityToDto(employeeDao.findByPosition(position));
    }

    @Override
    public List<EmployeeResponseDto> getByPositionAndDepartment(String position, String department){
        if(employeeDao.findByPositionAndDepartment(position, department).isEmpty()){
            throw new ResourceNotFoundException("Employees");
        }
        return EmployeeConverter.entityToDto(employeeDao.findByPositionAndDepartment(position, department));
    }
    @Override
    public Employee add(EmployeeRequestDto employeeRequestDto){

        if(employeeDao.findByEmail(employeeRequestDto.getEmail()) != null){
            throw new ResourceAlreadyExistException("Employee", "email", employeeRequestDto.getEmail());
        }
        Employee employee = EmployeeConverter.dtoToEntity(employeeRequestDto);
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
