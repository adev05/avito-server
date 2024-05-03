package com.avito.notification.service;

import com.avito.notification.model.Notification;
import com.avito.notification.model.Role;
import com.avito.notification.repository.NotificationRepository;
import com.avito.notification.repository.RoleRepository;

import org.hibernate.mapping.Set;
import org.hibernate.query.Page;
import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Notification> readAll(int offset, int limit) {
        return notificationRepository.findAll(PageRequest.of(offset / limit, limit)).getContent();
    }

    @Override
    public Notification readById(int id) {
        return notificationRepository.getReferenceById(id);
    }

    @Override
    public List<Notification> readAllByRole(Role role, int offset, int limit) {
        List<Integer> rolesTo = new ArrayList<>();
        rolesTo.add(role.getId());
        return notificationRepository.findAllByRolesTo(rolesTo, PageRequest.of(offset / limit, limit));
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