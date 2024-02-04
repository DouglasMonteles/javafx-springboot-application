package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.repositories.RoleRepository;
import com.doug.jfx.store.repositories.UserRepository;
import com.doug.jfx.store.services.UserService;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final TableBuilder tableBuilder = new TableBuilderImpl<User>();

    private UserDTO loggedUser;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        var user = new User(userDTO);
        var roles = roleRepository.findAllByNames(user.getRoles().stream().map(Role::getAuthority).toList());

        user.getRoles().clear();
        user.getRoles().addAll(roles);

        var newUser = this.userRepository.save(user);

        return new UserDTO(newUser);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {
        var user = new User(userDTO);
        var roles = roleRepository.findAllByNames(user.getRoles().stream().map(Role::getAuthority).toList());

        user.getRoles().clear();
        user.getRoles().addAll(roles);

        var newUser = this.userRepository.save(user);

        return new UserDTO(newUser);
    }

    @Override
    public UserDTO inactive(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow();

        user.setActive(false);
        user = userRepository.save(user);

        return new UserDTO(user);
    }

    @Override
    public boolean login(String username, String password) {
        UserDTO userDTO = userRepository.login(username, password);

        if (userDTO != null) {
            this.loggedUser = userDTO;
            return true;
        }

        return false;
    }

    @Override
    public UserDTO getLoggedUser() {
        return findAll().getFirst();
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

    @Override
    public void updateTableData() {
        var users = findAll();
        tableBuilder.setData(users);
    }

}
