package com.example.management_system.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class LoginRequest {

    @Id
    private int id;

    private String username;
    private String password;
}
