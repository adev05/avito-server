package com.avito.notification.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "notifications")
public class Notification {
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

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToMany
    @JoinTable(name = "notification_role",
        joinColumns = @JoinColumn(name = "notification_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public Notification() {
    }

    public Notification(String title, String description, User author, NotificationType type, List<Role> roles) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
        this.roles = roles;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRolesTo(List<Role> roles) {
        this.roles = roles;
    }
}
