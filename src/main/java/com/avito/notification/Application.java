package com.avito.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avito.notification.model.*;
import com.avito.notification.service.*;


@SpringBootApplication
public class Application {

    private final UserService userService;
    private final RoleService roleService;
    private final NotificationTypeService notificationTypeService;

    public Application(UserService userService, RoleService roleService, NotificationTypeService notificationTypeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.notificationTypeService = notificationTypeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private void init() {
        String[] roles = {"admin", "worker", "user"};
        String[] users = {"admin", "worker", "user"};
        for (int i = 0; i < roles.length; i++) {
            Role role = new Role();
            role.setRoleName(roles[i]);
            roleService.create(role);

            User user = new User();
            user.setUsername(users[i]);
            user.setPasswordHash(users[i]);
            user.setRole(role);
            userService.create(user);
    
        }

        String[] notificationTypes = {"default", "success", "info", "warning", "error"};
        for (String notificationTypeString : notificationTypes) {
            NotificationType notificationType = new NotificationType();
            notificationType.setTypeName(notificationTypeString);
            notificationTypeService.create(notificationType);
        }
    }
}
