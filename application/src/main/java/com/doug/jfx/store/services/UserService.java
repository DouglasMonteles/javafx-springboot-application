package com.doug.jfx.store.services;

import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.models.dtos.UserDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO insert(UserBuilder userBuilder);

    TableView<?> buildUserTable();

    void updateTableData();

}
