package com.avito.notification.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.*;
import com.avito.notification.service.*;

@RestController
public class TestController {
    private final UserService userService;
    private final RoleService roleService;

    public TestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @GetMapping(value = "/user/create")
    public ResponseEntity<?> autoCreateUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPasswordHash("admin");
        Role role = roleService.readById(1);
        user.setRole(role);
        userService.create(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
