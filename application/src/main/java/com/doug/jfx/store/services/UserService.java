package com.doug.jfx.store.services;

import com.doug.jfx.store.models.dtos.UserDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO insert(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    TableView<?> buildUserTable();

    void updateTableData();

    UserDTO inactive(Long id);
}
