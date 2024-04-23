package com.avito.notification.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;


@Entity
@Table(name = "clients")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setUsername(String name) {
        this.name = name;
    }
 
    public String getPasswordHash() {
        return email;
    }
 
    public void setPasswordHash(String email) {
        this.email = email;
    }
 
    public String getRole() {
        return phone;
    }
 
    public void setRole(String phone) {
        this.phone = phone;
    }
 }