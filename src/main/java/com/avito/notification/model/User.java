package com.avito.notification.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password_hash")
    private String passwordHash;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private Role role;
    
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public Role getRole() {
    	return role;
    }
    
    public void setRole(Role role) {
    	this.role = role;
    }
    
    public LocalDateTime getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }

    public void setPasswordHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordHash = passwordEncoder.encode(password);
    }

    public boolean checkPasswordHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.passwordHash);
    }
    
}