package com.avito.notification.service;

import java.util.List;

import com.avito.notification.model.Notification;
import com.avito.notification.model.Role;

public interface NotificationService {
    
    /**
     * Создает новое уведомление
     * @param notification - новое уведомление
     */
    void create(Notification notificationType);
 
    /**
     * Возвращает список всех имеющихся уведомлений
     * @return список уведомлений
     */
    List<Notification> readAll(int offset, int limit);
 
    /**
     * Возвращает уведомление по его id
     * @param id - id уведомления
     * @return - объект уведомления с заданным id
     */
    Notification readById(int id);

    // Notification setRoles(Notification notification, List<Role> roles);

    /**
     * Возвращает уведомление по его id
     * @param id - id уведомления
     * @return - объект уведомления с заданным id
     */
    List<Notification> readAllByRole(Role role, int offset, int limit);
 
    /**
     * Удаляет тип уведомления с заданным ID
     * @param id - id роли, которую нужно удалить
     * @return - true если роль была удалена, иначе false
     */
    boolean delete(int id);
}
