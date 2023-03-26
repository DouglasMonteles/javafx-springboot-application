package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.SideOptionsComponent;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.CategoryService;
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

    @Autowired
    private CategoryService categoryService;

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

    @FXML
    private MenuItem listCategoriesMenuItem;

    @FXML
    private MenuItem insertCategoryMenuItem;

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
        var sideOptionsContent = new SideOptionsComponent();

        userTableContent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            int selectedIndex = userTableContent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedUser = (UserDTO) userTableContent.getItems().get(selectedIndex);

                UserController.setSelectedUser(selectedUser);

                sideOptionsContent.setInfoAction(() -> {
                    Routes.redirectTo(Routes.INFO_USER);
                });

                sideOptionsContent.setEditAction(() -> {
                    Routes.redirectTo(Routes.UPDATE_USER);
                });

                sideOptionsContent.setDeleteAction(() -> {
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
            }

        });

        userTableContent.setMinWidth(800);
        userTableContent.getSelectionModel().selectFirst();

        HBox.setHgrow(userTableContent, Priority.ALWAYS);
        HBox.setHgrow(sideOptionsContent, Priority.ALWAYS);

        mainContainer.getChildren().addAll(userTableContent, sideOptionsContent);

        borderPane.setCenter(mainContainer);
    }

    @FXML
    public void redirectToHome(ActionEvent event) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

    public void listCategories(ActionEvent actionEvent) {
        var mainContainer = new VBox();

        var categoryTableComponent = categoryService.buildCategoryTable();
        var sideOptionsComponent = new SideOptionsComponent();

        categoryTableComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            int selectedIndex = categoryTableComponent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedCategory = (CategoryDTO) categoryTableComponent.getItems().get(selectedIndex);

                CategoryController.setSelectedCategory(selectedCategory);

                sideOptionsComponent.setInfoAction(() -> {

                });

                sideOptionsComponent.setEditAction(() -> {

                });

                sideOptionsComponent.setDeleteAction(() -> {
                    Dialog.confirmationDialog("Exclusão de categoria", "Deseja realmente apagar essa categoria?", "Se clicar em confirmar, a categoria será inativado do sistema.")
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(response -> {
//                                var userDTO = userService.inactive(selectedUser.getId());

//                                if (!userDTO.isActive()) {
//                                    Dialog.infoDialog("Exclusão de usuário", "Usuário inativado com sucesso!", "Usuário " + userDTO.getName() + " foi inativado");
//                                    userService.updateTableData();
//                                } else {
//                                    Dialog.errorDialog("Exclusão de usuário", "Não foi possível inativar o usuário", defaultErrorMessage);
//                                }
                            });
                });
            }
        });

        categoryTableComponent.setMinWidth(800);
        categoryTableComponent.getSelectionModel().selectFirst();

        HBox.setHgrow(categoryTableComponent, Priority.ALWAYS);
        HBox.setHgrow(sideOptionsComponent, Priority.ALWAYS);

        mainContainer.getChildren().addAll(categoryTableComponent, sideOptionsComponent);

        borderPane.setCenter(mainContainer);
    }

    public void handleCategoryRegister(ActionEvent actionEvent) {
    }
}
