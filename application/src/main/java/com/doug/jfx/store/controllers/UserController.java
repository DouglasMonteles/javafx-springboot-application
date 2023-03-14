package com.doug.jfx.store.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

    @FXML
    private MFXButton btnInsertUser;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXCheckbox isUserActive;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXTextField phone1;

    @FXML
    private MFXTextField phone2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void insertUser(ActionEvent event) {

    }

}
