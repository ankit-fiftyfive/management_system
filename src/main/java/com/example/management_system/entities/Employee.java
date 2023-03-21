package com.example.management_system.entities;

import jakarta.persistence.Column;
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
    private String street_address;
    @NotNull
    private long contact_number;

    @Email(message = "Enter valid mail address")
    @Column(unique = true)
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

    public Employee(long id, String name, int age, String gender, String position, long salary, String street_address, long contact_number, String email, String location, int pincode, String company, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.salary = salary;
        this.street_address = street_address;
        this.contact_number = contact_number;
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

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public long getContact_number() {
        return contact_number;
    }

    public void setContact_number(long contact_number) {
        this.contact_number = contact_number;
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
