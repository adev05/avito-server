package com.avito.notification.service;

import com.avito.notification.model.Role;
import com.avito.notification.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> readAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role readById(int id) {
        return roleRepository.getReferenceById(id);
    }

    @Override
    public Role readByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public boolean update(Role role, int id) {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}