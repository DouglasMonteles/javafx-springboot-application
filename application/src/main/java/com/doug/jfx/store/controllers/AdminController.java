package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.UserService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

            if (selectedIndex >= 0) {
                var selectedUser = (UserDTO) userTable.getItems().get(selectedIndex);

                var infoButton = new MFXButton("Ver", 140, 40);
                infoButton.setStyle("-fx-background-color:#ccc;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                infoButton.setOnMouseClicked(infoEvent -> {});

                var editButton = new MFXButton("Editar", 140, 40);
                editButton.setStyle("-fx-background-color:orange;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                editButton.setOnMouseClicked(editEvent -> {
                    UserController.userData = selectedUser;
                    Routes.redirectTo(Routes.UPDATE_USER);
                });

                var deleteButton = new MFXButton("Excluir", 140, 40);
                deleteButton.setStyle("-fx-background-color:red;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                deleteButton.setOnMouseClicked(deleteEvent -> {});

                selectedUserInformation.getChildren().clear();
                selectedUserInformation.getChildren().addAll(
                        new HBox(infoButton),
                        new HBox(editButton),
                        new HBox(deleteButton)
                );
            }
        });

        userTable.setMinWidth(800);
        userTable.getSelectionModel().selectFirst();

        selectedUserInformation.setAlignment(Pos.TOP_CENTER);
        selectedUserInformation.setSpacing(10);
        selectedUserInformation.setPadding(new Insets(10));

        container.setAlignment(Pos.CENTER_LEFT);

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
