package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AdminController implements Initializable {

    @Autowired
    private UserService userService;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem home;

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
        redirectToHome(null);
    }

    @FXML
    public void handleUserRegister(ActionEvent event) throws IOException {
        Routes.redirectTo(Routes.INSERT_USER);
    }

    @FXML
    public void listUsers(ActionEvent event) {
        var userTable = userService.buildUserTable();
        var container = new HBox();
        var selectedUserInformation = new VBox();

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
            var selectedUser = (UserDTO) userTable.getItems().get(selectedIndex);

            selectedUserInformation.getChildren().clear();
            selectedUserInformation.getChildren().addAll(
                    new HBox(new Text("Dados do usuário selecinado")),
                    new HBox(new Text("Nome: " + selectedUser.getName())),
                    new HBox(new Text("E-mail: " + selectedUser.getEmail())),
                    new HBox(new Text("Telefone(s): " + selectedUser.getPhones().toString())),
                    new HBox(new Text("Perfis: " + selectedUser.getRoles().toString())),
                    new HBox(new Text("Ativo: " + selectedUser.isActive()))
            );
        });

        userTable.setMinWidth(800);
        userTable.getSelectionModel().selectFirst();

        container.setAlignment(Pos.CENTER_LEFT);
        container.setSpacing(20);

        HBox.setHgrow(userTable, Priority.ALWAYS);
        HBox.setHgrow(selectedUserInformation, Priority.NEVER);
        container.getChildren().addAll(userTable, selectedUserInformation);

        borderPane.setCenter(container);
    }

    @FXML
    public void redirectToHome(ActionEvent event) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

}
