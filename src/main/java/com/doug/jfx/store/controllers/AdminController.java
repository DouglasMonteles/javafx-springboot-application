package com.doug.jfx.store.controllers;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AdminController implements Initializable {

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

    }

}
