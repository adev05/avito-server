package com.avito.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.avito.notification.model.NotificationType;
import com.avito.notification.model.Role;
import com.avito.notification.model.User;
import com.avito.notification.service.NotificationTypeService;
import com.avito.notification.service.RoleService;
import com.avito.notification.service.UserService;

@SpringBootApplication
@EnableScheduling
public class Application {

    private final UserService userService;
    private final RoleService roleService;
    private final NotificationTypeService notificationTypeService;
    // private final SocketIOServer server;
    // private final SocketService socketService;

    public Application(UserService userService, RoleService roleService, NotificationTypeService notificationTypeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.notificationTypeService = notificationTypeService;
        // this.server = server;
        // this.socketService = socketService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private void init() {
    	// this.server.start();
    	
    	// this.server.addEventListener("message", String.class, (client, data, ackRequest) -> {
        //     System.out.println("Received message from client: " + data);
        //     ackRequest.sendAckData("Received your message: " + data);
        // });
    	
        String[] roles = {"admin", "employee", "user"};
        String[] users = {"admin", "employee", "user"};
        for (int i = 0; i < 3; i++) {
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
    
    // @Autowired
    // private DataListener<Notification> onChatReceived() {
    //     return (senderClient, data, ackSender) -> {
    //         socketService.sendMessage("room", "send", senderClient, "message");
    //     };
    // }
}
