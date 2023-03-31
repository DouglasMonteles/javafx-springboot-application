package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormUserRegisterController;
import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.UserService;
import com.doug.jfx.store.services.RoleService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static UserDTO userDTO;

    @FXML
    private VBox insertUserContainer;

    @FXML
    private VBox updateUserContainer;

    @FXML
    private VBox infoUserContainer;

    @FXML
    private MFXButton insertUserButton;

    @FXML
    private MFXButton updateUserButton;

    @Value("${messages.insert_user.title}")
    private String insertUserTitle;

    @Value("${messages.insert_user.success}")
    private String insertUserSuccessMessage;

    @Value("${messages.insert_user.error}")
    private String insertUserErrorMessage;

    @Value("${messages.update_user.title}")
    private String updateUserTitle;

    @Value("${messages.update_user.success}")
    private String updateUserSuccessMessage;

    @Value("${messages.update_user.error}")
    private String updateUserErrorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertUserContainer != null) {
            var formInsertUser = new FormUserRegisterController(this.roleService);
            formInsertUser.setTitle("Cadastro de Usuário");
            formInsertUser.setSubmitAction((SubmitAction<UserDTO>) this::insertUser);

            insertUserContainer.getChildren().add(formInsertUser);
        }

        if (updateUserContainer != null) {
            var formUpdateUser = new FormUserRegisterController(this.roleService);
            formUpdateUser.setTitle("Atualização de Usuário");
            formUpdateUser.setSubmitAction((SubmitAction<UserDTO>) this::updateUser);
            formUpdateUser.setUserDTO(userDTO);

            updateUserContainer.getChildren().clear();
            updateUserContainer.getChildren().add(formUpdateUser);
        }

        if (infoUserContainer != null) {
            var formInfoUser = new FormUserRegisterController(this.roleService);
            formInfoUser.setTitle("Informações sobre o usuário");
            formInfoUser.setFormDisabled(true);
            formInfoUser.setUserDTO(userDTO);

            infoUserContainer.getChildren().clear();
            infoUserContainer.getChildren().add(formInfoUser);
        }
    }

    private void updateUser(UserDTO updatedUserDTO) {
        updatedUserDTO.setId(userDTO.getId());

        var userDTO = userService.update(updatedUserDTO);

        boolean isRegisteredUser = userDTO.getId() > 0;

        if (isRegisteredUser) {
            userService.updateTableData();
            Dialog.infoDialog(updateUserTitle, updateUserSuccessMessage, "Usuário " + userDTO.getName() + " atualizado!");
            Routes.UPDATE_USER.close();
        } else {
            Dialog.errorDialog(updateUserTitle, updateUserErrorMessage, defaultErrorMessage);
        }
    }

    private void insertUser(UserDTO newUserDTO) {
        var newUser = userService.insert(newUserDTO);

        boolean isRegisteredUser = newUser.getId() > 0;

        if (isRegisteredUser) {
            userService.updateTableData();
            Dialog.infoDialog(insertUserTitle, insertUserSuccessMessage, "Usuário " + newUser.getName() + " cadastrado!");
            Routes.INSERT_USER.close();
        } else {
            Dialog.errorDialog(insertUserTitle, insertUserErrorMessage, defaultErrorMessage);
        }
    }

    public static void setSelectedUser(UserDTO userDTO) {
        UserController.userDTO = userDTO;
    }

}
