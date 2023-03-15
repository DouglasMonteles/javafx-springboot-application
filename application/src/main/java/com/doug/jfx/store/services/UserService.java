package com.doug.jfx.store.services;

import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User insert(User user) {
        user.getRoles().add(new Role(1L, null));
        var newUser = this.userRepository.save(user);
        return newUser;
    }

}
