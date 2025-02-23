package com.excelr.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Email(message = "Enter the correct email format")
    private String email;
    private Double phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private Product product;
}
