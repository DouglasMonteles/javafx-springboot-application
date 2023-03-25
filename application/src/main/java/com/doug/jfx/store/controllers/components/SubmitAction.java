package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.models.dtos.UserDTO;

public interface SubmitAction {

    void handleSubmit(UserDTO userDTO);

}
