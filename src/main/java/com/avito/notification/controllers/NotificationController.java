package com.avito.notification.controllers;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("notification")
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

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNotification(@RequestBody ObjectNode objectNode) {
        String title = objectNode.get("title").asText();
        String description = objectNode.get("description").asText();
        String authorId = objectNode.get("author").asText();
        ArrayNode rolesTo = (ArrayNode) objectNode.get("roles_to");

        Integer[] rolesToInteger = new Integer[rolesTo.size()];
        for (int i = 0; i < rolesTo.size(); i++) {
            rolesToInteger[i] = roleService.readByRoleName(rolesTo.get(i).asText()).getId();
        }
        
        Notification newNotification = new Notification(title, description, userService.readById(Integer.parseInt(authorId)), notificationTypeService.readById(1), rolesToInteger);
        notificationService.create(newNotification);

        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }

    
}
