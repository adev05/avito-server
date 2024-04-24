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
    private final UserService userService;
    private final RoleService roleService;

    public NotificationController(NotificationService notificationService, UserService userService, RoleService roleService) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRole(@RequestBody ObjectNode objectNode) {
        // final JsonNode roles_to = new ObjectMapper().readTree(objectNode.toPrettyString()).get("roles_to");
        String title = objectNode.get("title").asText();
        String description = objectNode.get("description").asText();
        String author_id = objectNode.get("author").asText();
        ArrayNode roles_to = (ArrayNode) objectNode.get("roles_to");

        for (JsonNode jsonNode : roles_to) {
            System.out.println(roleService.readByRoleName(jsonNode.asText()).getId());
        }
        
        Notification newNotification = new Notification();

        newNotification.setTitle(title);
        newNotification.setDescription(description);
        newNotification.setAuthor(userService.readById(Integer.parseInt(author_id)));

        // newNotification.setRoles_to(roleService.readById(Integer.parseInt(roles_to.get(0).asText())));

        return new ResponseEntity<>(objectNode.get("roles_to"), HttpStatus.CREATED);
    }
}
