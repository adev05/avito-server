package com.avito.notification.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.Role;
import com.avito.notification.model.User;
import com.avito.notification.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        roleService.create(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        final List<Role> users = roleService.readAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
