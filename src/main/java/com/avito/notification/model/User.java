package com.avito.notification.model;

import java.util.List;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username")
    private String username;

    @JsonIgnore
    @Column(name="password_hash")
    private String passwordHash;
    
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;
    
    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Notification> notifications;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        setPasswordHash(password);
    }
 
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
    
    public Timestamp getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
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