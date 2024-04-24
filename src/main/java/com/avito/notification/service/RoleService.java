package com.avito.notification.service;

import java.util.List;
import com.avito.notification.model.Role;

public interface RoleService {

    /**
     * Создает новую роль
     * @param role - роль для создания
     */
    void create(Role role);
 
    /**
     * Возвращает список всех имеющихся ролей
     * @return список ролей
     */
    List<Role> readAll();
 
    /**
     * Возвращает роль по его ID
     * @param id - ID роли
     * @return - объект роли с заданным ID
     */
    Role read(int id);
 
    /**
     * Обновляет роль с заданным ID,
     * в соответствии с переданной ролью
     * @param client - роль в соответсвии с которой нужно обновить данные
     * @param id - id роли которую нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Role client, int id);
 
    /**
     * Удаляет роль с заданным ID
     * @param id - id роли, которую нужно удалить
     * @return - true если роль была удалена, иначе false
     */
    boolean delete(int id);
 }
 