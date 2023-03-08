package com.doug.jfx.store.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
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
            System.out.println(username.isValid());
            System.out.println(password);
        }
    }

    @FXML
    void doForgotPasswordRecovery(MouseEvent event) {
        System.out.println("Esqueceu a senha.");
    }

    private void validateLogin() {
        this.loginButton.setDisable(true);

        this.username.textProperty().addListener((observable, oldValue, newValue) -> {
            this.loginButton.setDisable(username.getText().isEmpty() || password.getText().isEmpty());
        });

        this.password.textProperty().addListener((observable, oldValue, newValue) -> {
            this.loginButton.setDisable(username.getText().isEmpty() || password.getText().isEmpty());
        });
    }

}
