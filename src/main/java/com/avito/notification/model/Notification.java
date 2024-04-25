package com.avito.notification.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.Time;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "notifications")
public class Notification implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @OneToOne
    @JoinColumn(name = "type")
    private NotificationType type;

    @Column(name = "roles_to")
    private Integer[] rolesTo;

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public Notification() {
    }

    public Notification(String title, String description, User author, NotificationType type, Integer[] rolesTo) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
        this.rolesTo = rolesTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;   
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }  

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Integer[] getRolesTo() {
        return rolesTo;
    }

    public void setRolesTo(Integer[] rolesTo) {
        this.rolesTo = rolesTo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
