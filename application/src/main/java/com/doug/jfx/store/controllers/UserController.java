package com.doug.jfx.store.controllers;

import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.builders.impl.UserBuilderImpl;
import com.doug.jfx.store.enums.Role;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.helpers.Validators;
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

    public static UserDTO userData;

    @FXML
    private MFXButton insertUserButton;

    @FXML
    private MFXButton updateUserButton;

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
    private String insertUserTitle;

    @Value("${messages.insert_user.success}")
    private String insertUserSuccessMessage;

    @Value("${messages.insert_user.error}")
    private String insertUserErrorMessage;

    @Value("${messages.update_user.title}")
    private String updateUserTitle;

    @Value("${messages.update_user.success}")
    private String updateUserSuccessMessage;

    @Value("${messages.update_user.error}")
    private String updateUserErrorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertUserButton != null) {
            insertUserButton.setDisable(true);
            validateRequiredFields(insertUserButton);
        }

        if (updateUserButton != null) {
            var phones = userData.getPhones().stream().toList();

            updateUserButton.setDisable(true);
            name.setText(userData.getName());
            email.setText(userData.getEmail());
            password.setText(userData.getPassword());

            if (phones.size() == 1) {
                phone1.setText(phones.get(0));
            } else if (phones.size() == 2) {
                phone1.setText(phones.get(0));
                phone2.setText(phones.get(1));
            }

            isUserActive.setSelected(userData.isActive());

            updateUserButton.setDisable(false);
            validateRequiredFields(updateUserButton);
        }
    }

    @FXML
    public void insertUser(ActionEvent event) {
        UserBuilder userBuilder = new UserBuilderImpl();

        userBuilder.setName(name.getText())
                .setEmail(email.getText())
                .setPassword(password.getText())
                .setPhone1(phone1.getText())
                .setPhone2(phone2.getText())
                .setRoles(List.of(Role.CLIENT.getDescription()))
                .isActive(isUserActive.isSelected());

        var userDTO = userService.insert(userBuilder);

        boolean isRegisteredUser = userDTO.getId() > 0;

        if (isRegisteredUser) {
            userService.updateTableData();
            Dialog.infoDialog(insertUserTitle, insertUserSuccessMessage, "Usuário " + userDTO.getName() + " cadastrado!");
            Routes.INSERT_USER.close();
        } else {
            Dialog.errorDialog(insertUserTitle, insertUserErrorMessage, defaultErrorMessage);
        }
    }

    @FXML
    public void updateUser(ActionEvent event) {
        UserBuilder userBuilder = new UserBuilderImpl();

        userBuilder.setId(userData.getId())
                .setName(name.getText())
                .setEmail(email.getText())
                .setPassword(password.getText())
                .setPhone1(phone1.getText())
                .setPhone2(phone2.getText())
                .setRoles(List.of(Role.CLIENT.getDescription()))
                .isActive(isUserActive.isSelected());

        var userDTO = userService.update(userBuilder);

        boolean isRegisteredUser = userDTO.getId() > 0;

        if (isRegisteredUser) {
            userService.updateTableData();
            Dialog.infoDialog(updateUserTitle, updateUserSuccessMessage, "Usuário " + userDTO.getName() + " atualizado!");
            Routes.UPDATE_USER.close();
        } else {
            Dialog.errorDialog(updateUserTitle, updateUserErrorMessage, defaultErrorMessage);
        }
    }

    private void validateRequiredFields(MFXButton submitButton) {
        var requiredFields = List.of(name, email, password);
        Validators.validateFormSubmitButton(requiredFields, submitButton);
    }

    private UserDTO getUserData() {
        return userData;
    }

}
