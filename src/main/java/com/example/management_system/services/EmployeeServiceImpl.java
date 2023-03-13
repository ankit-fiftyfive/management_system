package com.example.management_system.services;

import com.example.management_system.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> list;

    public EmployeeServiceImpl(){
        list = new ArrayList<>();
        list.add(new Employee(1, "Ankit", 21, "Male", "Java Developer", 50000));
        list.add(new Employee(2, "Ashu", 22, "Male", ".Net Developer", 40000));
    }

    @Override
    public List<Employee> getAll() {
        return list;
    }

    @Override
    public Employee getById(long id) {
        for(int i = 0;i< list.size();i++){
            if(list.get(i).getId() == id){
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public Employee add(Employee employee) {
        list.add(employee);
        return employee;
    }

    @Override
    public String update(Employee employee) {
        for (Employee emp : list) {
            if (emp.getId() == employee.getId()) {
                emp.setName(employee.getName());
                emp.setAge(employee.getAge());
                emp.setGender(employee.getGender());
                emp.setPosition(employee.getPosition());
                emp.setSalary(employee.getSalary());
                return "Updated Successfully";
            }
        }
        return "Employee is not present";
    }

    @Override
    public String deleteById(long id) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId() == id){
                list.remove(i);
            }
        }
        return "Deleted Successfully";
    }

    @Override
    public String deleteAll() {
        list.clear();
        return "All records deleted";
    }
}
