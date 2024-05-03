package com.avito.notification.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Notification> notifications = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;   
    }

    public String getName() {
        return name;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }
}
