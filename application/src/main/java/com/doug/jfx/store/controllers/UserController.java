package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Role;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.helpers.Validators;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.impl.UserServiceImpl;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

    @Autowired
    private UserServiceImpl userService;

    @FXML
    private MFXButton insertUserButton;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXCheckbox isUserActive;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXTextField phone1;

    @FXML
    private MFXTextField phone2;

    @Value("${messages.insert_user.title}")
    private String screenTitle;

    @Value("${messages.insert_user.success}")
    private String successMessage;

    @Value("${messages.insert_user.error}")
    private String errorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertUserButton.setDisable(true);
        validateRequiredFields();
    }

    @FXML
    public void insertUser(ActionEvent event) {
        var userDTO = new UserDTO(null, name.getText(), email.getText(), password.getText(), List.of(Role.CLIENT.getDescription()), isUserActive.isSelected());
        userDTO = userService.insert(userDTO);

        boolean isRegisteredUser = userDTO.id() > 0;

        if (isRegisteredUser) {
            Dialog.infoDialog(screenTitle, successMessage, "Usuário " + userDTO.name() + " cadastrado!");
            Routes.INSERT_USER.close();
        } else {
            Dialog.errorDialog(screenTitle, errorMessage, defaultErrorMessage);
        }
    }

    private void validateRequiredFields() {
        var requiredFields = List.of(name, email, password);
        Validators.validateFormSubmitButton(requiredFields, insertUserButton);
    }

}
