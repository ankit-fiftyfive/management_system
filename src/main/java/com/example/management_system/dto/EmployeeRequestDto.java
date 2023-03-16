package com.example.management_system.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequestDto {
    @NotNull
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
    @Column(unique = true)
    @Email(message = "Enter valid mail address")
    private String email;
    @NotEmpty
    private String location;
    @NotNull
    private int pincode;
    @NotEmpty
    private String company;
    @NotEmpty
    private String department;
}
