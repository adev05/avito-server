package com.avito.notification.service;

import java.util.List;
import com.avito.notification.model.*;

public interface UserService {

    /**
     * Создает нового клиента
     * @param client - клиент для создания
     */
    void create(User client);
 
    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<User> readAll();
 
    /**
     * Возвращает клиента по его id
     * @param id - id клиента
     * @return - объект клиента с заданным id
     */
    User readById(int id);

    /**
     * Возвращает клиента по его username
     * @param username username клиента
     * @return - объект клиента с заданным username
     */
    User readByUsername(String username);
 
    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param client - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(User client, int id);
 
    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
 }
 