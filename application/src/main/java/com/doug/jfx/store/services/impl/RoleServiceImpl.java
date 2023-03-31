package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.repositories.RoleRepository;
import com.doug.jfx.store.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
