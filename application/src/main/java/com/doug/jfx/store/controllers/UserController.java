package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Validators;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.services.UserService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

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

    @Autowired
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertUserButton.setDisable(true);

        validateRequiredFields();
    }

    @FXML
    public void insertUser(ActionEvent event) {
        var user = new User(null, name.getText(), email.getText(), password.getText(), isUserActive.isSelected());
        user = userService.insert(user);

        boolean isRegisteredUser = user.getId() > 0;

        if (isRegisteredUser) {
            System.out.println("Usuário " + user.getName() + " cadastrado com sucesso!");
            Routes.INSERT_USER.close();
        } else {
            System.out.println("deu ruim :(");
        }
    }

    private void validateRequiredFields() {
        var requiredFields = List.of(name, email, password);
        Validators.validateFormSubmitButton(requiredFields, insertUserButton);
    }

}
