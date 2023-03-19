package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Validators;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private MFXButton loginButton;

    @FXML
    private MFXTextField username;

    @FXML
    private MFXPasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.validateLogin();
    }

    @FXML
    void doLogin(ActionEvent event) {
        if (username.getText() != null && password.getText() != null) {
            Routes.LOGIN.close();
            Routes.redirectTo(Routes.ADMIN);
        }
    }

    @FXML
    void doForgotPasswordRecovery(MouseEvent event) {
        System.out.println("Esqueceu a senha.");
    }

    private void validateLogin() {
        this.loginButton.setDisable(true);
        Validators.validateFormSubmitButton(List.of(username, password), loginButton);
    }

}
