package com.avito.notification.service;

import com.avito.notification.model.NotificationType;
import com.avito.notification.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Override
    public void create(NotificationType notificationType) {
        notificationTypeRepository.save(notificationType);
    }

    @Override
    public List<NotificationType> readAll() {
        return notificationTypeRepository.findAll();
    }

    @Override
    public NotificationType readById(int id) {
        return notificationTypeRepository.getReferenceById(id);
    }

    @Override
    public NotificationType readByName(String typeName) {
        return notificationTypeRepository.findByTypeName(typeName);
    }

    @Override
    public boolean update(NotificationType notificationType, int id) {
        if (notificationTypeRepository.existsById(id)) {
            notificationType.setId(id);
            notificationTypeRepository.save(notificationType);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (notificationTypeRepository.existsById(id)) {
            notificationTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}