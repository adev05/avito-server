package com.avito.notification.service;

import com.avito.notification.model.*;
import com.avito.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User client) {
        userRepository.save(client);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(int id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public boolean update(User client, int id) {
        if (userRepository.existsById(id)) {
            client.setId(id);
            userRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}