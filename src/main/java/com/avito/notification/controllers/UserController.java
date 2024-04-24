package com.avito.notification.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.model.*;
import com.avito.notification.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public ResponseEntity <?> getAllUsers() {
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity <?> findById(@RequestBody int id) {
        return new ResponseEntity<>(userService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity <?> findByUsername(@RequestBody String username) {
        return new ResponseEntity<>(userService.readByUsername(username), HttpStatus.OK);
    }

    // @GetMapping(value = "/getAllByRole/{role}")
    // public ResponseEntity<?> getAllByRole(@RequestBody String[] roles) {

    //     for (String role : roles) {
            
    //     }
    //     return new ResponseEntity<>(role.getUsers(), HttpStatus.OK);
    // }
    

    @PostMapping(value = "/signin")
    public ResponseEntity<User> signIn(@RequestBody ObjectNode objectNode) {
        String username = objectNode.get("username").asText();
        String password = objectNode.get("password").asText();

        User user = userService.readByUsername(username);
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
