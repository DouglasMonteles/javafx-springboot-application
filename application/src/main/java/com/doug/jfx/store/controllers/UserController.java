package com.doug.jfx.store.controllers;

import com.doug.jfx.store.helpers.Validators;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertUserButton.setDisable(true);

        validateRequiredFields();
    }

    @FXML
    void insertUser(ActionEvent event) {

    }

    private void validateRequiredFields() {
        var requiredFields = List.of(name, email, password);
        Validators.validateFormSubmitButton(requiredFields, insertUserButton);
    }

}
