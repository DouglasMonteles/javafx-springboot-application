package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.repositories.RoleRepository;
import com.doug.jfx.store.repositories.UserRepository;
import com.doug.jfx.store.services.UserService;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final TableBuilder tableBuilder = new TableBuilderImpl<User>();

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        var user = new User(null, userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.isActive());
        var roles = roleRepository.findAllByNames(userDTO.getRoles());
        user.getRoles().addAll(roles);

        var newUser = this.userRepository.save(user);

        return new UserDTO(newUser);
    }

    @Override
    public TableView<?> buildUserTable() {
        var users = findAll();

        return tableBuilder
                .addColumn("Id", "id")
                .addColumn("Nome", "name")
                .addColumn("E-mail", "email")
                .addColumn("Perfis", "roles")
                .setData(users)
                .build();
    }

    public void updateTableData() {
        var users = findAll();
        tableBuilder.setData(users);
    }

}
