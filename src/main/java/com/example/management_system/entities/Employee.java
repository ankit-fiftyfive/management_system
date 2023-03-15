package com.example.management_system.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty(message = "name should not be null")
    private String name;
    @NotNull(message = "age should not be null")
    private int age;
    @NotEmpty(message = "please enter correct gender")
    private String gender;
    @NotEmpty
    private String position;
    @NotNull
    private long salary;
    @NotEmpty
    private String streetAddress;
    @NotNull
    private long contactNumber;
    @Email(message = "Enter valid mail address")
    @NotNull
    private String email;
    @NotEmpty
    private String location;
    @NotNull
    private int pincode;
    @NotEmpty
    private String company;
    @NotEmpty
    private String department;

    public Employee() {
    }

    public Employee(long id, String name, int age, String gender, String position, long salary, String streetAddress, long contactNumber, String email, String location, int pincode, String company, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.salary = salary;
        this.streetAddress = streetAddress;
        this.contactNumber = contactNumber;
        this.email = email;
        this.location = location;
        this.pincode = pincode;
        this.company = company;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
