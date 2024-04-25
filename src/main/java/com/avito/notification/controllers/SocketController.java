package com.avito.notification.controllers;

import com.avito.notification.model.Notification;
import com.avito.notification.service.NotificationService;
import com.avito.notification.service.NotificationTypeService;
import com.avito.notification.service.RoleService;
import com.avito.notification.service.UserService;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class SocketController {

    private static final Logger log = LoggerFactory.getLogger(SocketController.class);

    private final SocketIONamespace namespace;

    private final NotificationService notificationService;
    private final NotificationTypeService notificationTypeService;
    private final UserService userService;
    private final RoleService roleService;

    public SocketController(SocketIOServer server, NotificationService notificationService, UserService userService, 
    RoleService roleService, NotificationTypeService notificationTypeService) {
        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("createNotification", ObjectNode.class, onCreateNotification());
        this.namespace.addEventListener("send_message", String.class, sendMessage());

        this.notificationService = notificationService;
        this.userService = userService;
        this.roleService = roleService;
        this.notificationTypeService = notificationTypeService;
    }
    
    private DataListener<ObjectNode> onCreateNotification() {
        return (client, data, ackSender) -> {
            System.out.println("Message in chat");
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
            
            String title = data.get("title").asText();
            String description = data.get("description").asText();
            String authorId = data.get("author").asText();
            ArrayNode rolesTo = (ArrayNode) data.get("roles_to");

            Integer[] rolesToInteger = new Integer[rolesTo.size()];
            for (int i = 0; i < rolesTo.size(); i++) {
                rolesToInteger[i] = roleService.readByRoleName(rolesTo.get(i).asText()).getId();
            }
            
            Notification newNotification = new Notification(title, description, userService.readById(Integer.parseInt(authorId)), notificationTypeService.readById(1), rolesToInteger);
            notificationService.create(newNotification);

            namespace.getBroadcastOperations().sendEvent("chat", newNotification);
        };
    }

    private DataListener<String> sendMessage() {
        return (client, data, ackSender) -> {
            System.out.println(client);
            System.out.println(data);
            System.out.println(ackSender);

            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);

            namespace.getBroadcastOperations().sendEvent("send_message", data);
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            System.out.println("Connected");
            System.out.println(client.getAllRooms());
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }
}