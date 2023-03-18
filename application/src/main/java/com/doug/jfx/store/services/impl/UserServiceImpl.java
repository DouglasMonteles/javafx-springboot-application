package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.repositories.RoleRepository;
import com.doug.jfx.store.repositories.UserRepository;
import com.doug.jfx.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        var user = new User(null, userDTO.name(), userDTO.email(), userDTO.password(), userDTO.isActive());
        var roles = roleRepository.findAllByNames(userDTO.roles());
        user.getRoles().addAll(roles);

        var newUser = this.userRepository.save(user);

        return new UserDTO(newUser);
    }

}
