package com.doug.jfx.store.controllers;

import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.UserService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private MenuItem home;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem insertUserMenuItem;

    @FXML
    private MenuItem listUsersMenuItem;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

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
        var mainContainer = new HBox();

        var userTableContent = userService.buildUserTable();
        var optionsContent = new VBox();

        userTableContent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int selectedIndex = userTableContent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedUser = (UserDTO) userTableContent.getItems().get(selectedIndex);

                var infoButton = new MFXButton("Ver", 140, 40);
                infoButton.setStyle("-fx-background-color:#ccc;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                infoButton.setOnMouseClicked(infoEvent -> {
                    Routes.redirectTo(Routes.INFO_USER);
                });

                var editButton = new MFXButton("Editar", 140, 40);
                editButton.setStyle("-fx-background-color:orange;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                editButton.setOnMouseClicked(editEvent -> {
                    UserController.setSelectedUser(selectedUser);
                    Routes.redirectTo(Routes.UPDATE_USER);
                });

                var deleteButton = new MFXButton("Excluir", 140, 40);
                deleteButton.setStyle("-fx-background-color:red;-fx-text-fill:#fff;-fx-font-size: 1.3em;");
                deleteButton.setOnMouseClicked(deleteEvent -> {
                    Dialog.confirmationDialog("Exclusão de usuário", "Deseja realmente apagar teste usuário?", "Se clicar em confirmar, o usuário será inativado do sistema.")
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            var userDTO = userService.inactive(selectedUser.getId());

                            if (!userDTO.isActive()) {
                                Dialog.infoDialog("Exclusão de usuário", "Usuário inativado com sucesso!", "Usuário " + userDTO.getName() + " foi inativado");
                                userService.updateTableData();
                            } else {
                                Dialog.errorDialog("Exclusão de usuário", "Não foi possível inativar o usuário", defaultErrorMessage);
                            }
                        });
                });

                optionsContent.getChildren().clear();
                optionsContent.getChildren().addAll(
                        new HBox(infoButton),
                        new HBox(editButton),
                        new HBox(deleteButton)
                );
            }
        });

        userTableContent.setMinWidth(800);
        userTableContent.getSelectionModel().selectFirst();

        optionsContent.setAlignment(Pos.TOP_CENTER);
        optionsContent.setSpacing(10);
        optionsContent.setPadding(new Insets(10));

        mainContainer.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(userTableContent, Priority.ALWAYS);
        HBox.setHgrow(optionsContent, Priority.NEVER);
        mainContainer.getChildren().addAll(userTableContent, optionsContent);

        borderPane.setCenter(mainContainer);
    }

    @FXML
    public void redirectToHome(ActionEvent event) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

}
