package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdminController implements Initializable {

    @Autowired
    private UserService userService;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem insertUserMenuItem;

    @FXML
    private MenuItem listUsersMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

    @FXML
    public void handleUserRegister() throws IOException {
        Routes.redirectTo(Routes.INSERT_USER);
    }

}
