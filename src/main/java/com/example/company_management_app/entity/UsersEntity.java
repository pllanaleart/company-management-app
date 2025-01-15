package com.example.company_management_app.entity;

import jakarta.persistence.*;

@Entity(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private CompanyEntity company;
}
