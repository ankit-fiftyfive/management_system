package com.example.management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeSubDto {

    private long id;
    private String name;
    private String gender;
    private String position;
    private long contactNumber;
    private String email;
    private String location;
    private String company;
    private String department;
}
