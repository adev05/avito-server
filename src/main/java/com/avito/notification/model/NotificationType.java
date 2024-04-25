package com.avito.notification.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "notification_types")
public class NotificationType implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    @JsonIgnore
    @OneToOne(mappedBy = "type")
    private Notification notification;

    public NotificationType() {
    }

    public NotificationType(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;   
    }

    public String getTypeName() {
        return typeName;
    } 

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
