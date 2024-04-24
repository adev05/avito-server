package com.avito.notification.service;

import com.avito.notification.model.Notification;
import com.avito.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void create(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> readAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification readById(int id) {
        return notificationRepository.getReferenceById(id);
    }

    @Override
    public boolean delete(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}