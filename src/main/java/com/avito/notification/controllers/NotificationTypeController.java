package com.avito.notification.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avito.notification.service.NotificationTypeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/notification/type")
public class NotificationTypeController {
    private final NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNotificationType() {
        return new ResponseEntity<>(notificationTypeService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/by/id")
    public ResponseEntity<?> getNotificationTypeById(@RequestBody int id) {
        return new ResponseEntity<>(notificationTypeService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/by/name")
    public ResponseEntity<?> getNotificationTypeByName(@RequestBody String name) {
        return new ResponseEntity<>(notificationTypeService.readByName(name), HttpStatus.OK);
    }
    

}