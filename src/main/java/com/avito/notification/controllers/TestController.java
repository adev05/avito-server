package com.avito.notification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @GetMapping(value = "/autoCreateUsers")
    public ResponseEntity<?> autoCreateUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPasswordHash("admin");
        Role role = roleService.read(1);
        user.setRole(role);
        userService.create(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
