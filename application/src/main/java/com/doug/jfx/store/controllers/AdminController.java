package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.SideOptionsComponent;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.CategoryService;
import com.doug.jfx.store.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @FXML
    public MenuItem listProductsMenuItem;

    @FXML
    public MenuItem insertProductMenuItem;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        redirectToHome(null);
    }

    @FXML
    public void handleUserRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_USER);
    }

    @FXML
    public void handleCategoryRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_CATEGORY);
    }

    @FXML
    public void handleProductRegister(ActionEvent event) {
        Routes.redirectTo(Routes.INSERT_PRODUCT);
    }

    @FXML
    public void listUsers(ActionEvent event) {
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

        buildAdminScreen(userTableContent, sideOptionsContent);
    }

    @FXML
    public void redirectToHome(ActionEvent event) {
        borderPane.setCenter(new Label("Bem vindo!"));
    }

    public void listCategories(ActionEvent actionEvent) {
        var categoryTableComponent = categoryService.buildCategoryTable();
        var sideOptionsComponent = new SideOptionsComponent();

        categoryTableComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            int selectedIndex = categoryTableComponent.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                var selectedCategory = (CategoryDTO) categoryTableComponent.getItems().get(selectedIndex);

                CategoryController.setSelectedCategory(selectedCategory);

                sideOptionsComponent.setInfoAction(() -> {
                    Routes.redirectTo(Routes.INFO_CATEGORY);
                });

                sideOptionsComponent.setEditAction(() -> {
                    Routes.redirectTo(Routes.UPDATE_CATEGORY);
                });

                sideOptionsComponent.setDeleteAction(() -> {
                    var categoryName = selectedCategory.getName().toUpperCase();

                    Dialog.confirmationDialog("Exclusão de categoria", "Deseja realmente apagar essa categoria?", "Se clicar em confirmar, a categoria \"" + categoryName + "\" será excluída do sistema.")
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(response -> {
                                categoryService.delete(selectedCategory.getId());
                                categoryService.updateTableData();
                                categoryTableComponent.getSelectionModel().selectFirst();
                            });
                });
            }
        });

        buildAdminScreen(categoryTableComponent, sideOptionsComponent);
    }

    public void listProducts(ActionEvent actionEvent) {

    }

    public void handleExitApplication(ActionEvent actionEvent) {
        Dialog.confirmationDialog("Finalizar a aplicação", "Você está prestes a finalizar a execução da aplicação", "Tem certeza que deseja prosseguir?")
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.exit(0));
    }

    private void buildAdminScreen(TableView<?> centerComponent, Node rightComponent) {
        centerComponent.setMinWidth(800);
        centerComponent.getSelectionModel().selectFirst();

        HBox.setHgrow(centerComponent, Priority.ALWAYS);
        HBox.setHgrow(rightComponent, Priority.ALWAYS);

        borderPane.setCenter(centerComponent);
        borderPane.setRight(rightComponent);
    }

}
