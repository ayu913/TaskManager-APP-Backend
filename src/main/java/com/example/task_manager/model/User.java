package com.example.task_manager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private  String password;

    private Boolean isAdmin = false;

    private  String role;

    private  String title;

}
