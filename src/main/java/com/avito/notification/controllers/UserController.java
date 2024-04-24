package com.avito.notification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.*;
import com.avito.notification.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/signIn")
    public ResponseEntity<User> signIn(@RequestBody ObjectNode objectNode) {
        String username = objectNode.get("username").asText();
        String password = objectNode.get("password").asText();

        User user = userService.getByUsername(username);
        if (user == null) {
            return null;
        } else {
            if (user.checkPasswordHash(password)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return null;
            }
        }
    }
}
