package com.avito.notification.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.*;

import com.avito.notification.service.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationTypeService notificationTypeService;
    private final UserService userService;
    private final RoleService roleService;

    public NotificationController(NotificationService notificationService, UserService userService, RoleService roleService,
        NotificationTypeService notificationTypeService) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.roleService = roleService;
        this.notificationTypeService = notificationTypeService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getAllNotifications(@RequestBody ObjectNode objectNode) {
        int offset = objectNode.get("offset").asInt();
        int limit = objectNode.get("limit").asInt();

        List<Notification> notifications = notificationService.readAll(offset, limit);

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllByRoleName")
    public ResponseEntity<?> getAllNotificationsByRole(@RequestBody ObjectNode objectNode) {
        int offset = objectNode.get("offset").asInt();
        int limit = objectNode.get("limit").asInt();
        String roleTo = objectNode.get("roleTo").asText();
        
        Role role = roleService.readByRoleName(roleTo);
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(roleService.readByRoleName(roleTo).getId());

        List<Notification> notifications = notificationService.readAllByRole(role, offset, limit);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNotification(@RequestBody ObjectNode objectNode) {
        String title = objectNode.get("title").asText();
        String description = objectNode.get("description").asText();
        String authorId = objectNode.get("author").asText();
        ArrayNode rolesTo = (ArrayNode) objectNode.get("roles_to");

        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < rolesTo.size(); i++) {
            roles.add(roleService.readByRoleName(rolesTo.get(i).asText()));
        }
        
        Notification newNotification = new Notification(title, description, userService.readById(Integer.parseInt(authorId)), notificationTypeService.readById(1), roles);
        notificationService.create(newNotification);

        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }

    
}
