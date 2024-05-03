package com.avito.notification.repository;

import com.avito.notification.model.Notification;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>  {
    List<Notification> findAllByRolesTo(List<Integer> rolesTo, PageRequest pageRequest);
}