package com.avito.notification.service;

import java.util.List;

import com.avito.notification.model.NotificationType;

public interface NotificationTypeService {
    
    /**
     * Создает новый тип уведомлений
     * @param notificationType - тип уведомления для создания
     */
    void create(NotificationType notificationType);
 
    /**
     * Возвращает список всех имеющихся типов уведомлений
     * @return список типов уведомлений
     */
    List<NotificationType> readAll();
 
    /**
     * Возвращает тип уведомления по его id
     * @param id - id типа уведомления
     * @return - объект типа уведомления с заданным id
     */
    NotificationType readById(int id);

    /**
     * Возвращает тип уведомления по его type_name
     * @param name - type_name типа уведомления
     * @return - объект типа уведомления с заданным type_name
    */
    NotificationType readByName(String typeName);
 
    /**
     * Обновляет тип уведомления с заданным id, в соответствии с переданным типом
     * @param notificationType - тип уведомления в соответсвии с которым нужно обновить данные
     * @param id - id типа уведомления который нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(NotificationType notificationType, int id);
 
    /**
     * Удаляет тип уведомления с заданным ID
     * @param id - id роли, которую нужно удалить
     * @return - true если роль была удалена, иначе false
     */
    boolean delete(int id);
}
