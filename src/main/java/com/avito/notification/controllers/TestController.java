package com.avito.notification.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.Role;
import com.avito.notification.model.User;
import com.avito.notification.service.RoleService;
import com.avito.notification.service.UserService;

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
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPasswordHash("admin");
        Role role1 = roleService.readById(1);
        user1.setRole(role1);
        userService.create(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPasswordHash("user2");
        Role role2 = roleService.readById(2); // assuming you have a role with id 2
        user2.setRole(role2);
        userService.create(user2);

        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
}
