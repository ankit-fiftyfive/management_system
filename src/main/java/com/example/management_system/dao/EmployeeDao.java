package com.example.management_system.dao;


import com.example.management_system.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {

    public List<Employee> findByNameAndAge(String name, int age);

    public Employee findById(long id);

    public List<Employee> findBySalary(long salary);

    public Employee findByEmail(String email);

    @Query(value = "Select * From employee e Where e.position =?1", nativeQuery = true)
    public List<Employee> findByPosition(@Param("position") String position);

    @Query(value = "Select * From employee e Where e.position =?1 and e.department =?2", nativeQuery = true)
    public List<Employee> findByPositionAndDepartment(@Param("position") String position, @Param("department") String department);

}
