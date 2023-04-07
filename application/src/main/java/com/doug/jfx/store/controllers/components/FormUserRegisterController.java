package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.builders.impl.UserBuilderImpl;
import com.doug.jfx.store.helpers.Validators;
import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.dtos.UserDTO;
import com.doug.jfx.store.services.RoleService;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class FormUserRegisterController extends VBox implements Initializable {

    private final RoleService roleService;

    @FXML
    private Text title;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXCheckbox isUserActive;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXTextField phone1;

    @FXML
    private MFXTextField phone2;

    @FXML
    private MFXCheckListView<String> rolesCheckList;

    @FXML
    private MFXButton submitButton;

    private SubmitAction<UserDTO> submitAction;

    private UserDTO userDTO;

    private boolean isFormDisabled = false;

    public FormUserRegisterController(RoleService roleService) {
        this.roleService = roleService;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/form_user_register_component.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateRequiredFields(submitButton);
        submitButton.setDisable(true);

        var roles = roleService.findAll();

        rolesCheckList.setItems(FXCollections.observableArrayList(
                roles.stream().map(Role::getAuthority).toList()
        ));
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSubmitAction(SubmitAction<UserDTO> submitAction) {
        this.submitAction = submitAction;
    }

    public SubmitAction<UserDTO> getSubmitAction() {
        return submitAction;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        this.populateFormWithUserData();
    }

    public boolean isFormDisabled() {
        return isFormDisabled;
    }

    public void setFormDisabled(boolean formDisabled) {
        isFormDisabled = formDisabled;
    }

    @FXML
    private void submit() {
        UserBuilder userBuilder = new UserBuilderImpl();

        userBuilder.setName(name.getText())
                .setEmail(email.getText())
                .setPassword(password.getText())
                .setPhone1(phone1.getText())
                .setPhone2(phone2.getText())
                .setRoles(rolesCheckList.getSelectionModel().getSelectedValues())
                .isActive(isUserActive.isSelected());

        var userDTO = userBuilder.buildAndConvertToDTO();

        getSubmitAction().handleSubmit(userDTO);
    }

    private void populateFormWithUserData() {
        boolean isFormDisable = isFormDisabled();
        var phones = userDTO.getPhones().stream().toList();

        submitButton.setText("Atualizar usuário");

        name.setText(userDTO.getName());
        name.setDisable(isFormDisable);

        email.setText(userDTO.getEmail());
        email.setDisable(isFormDisable);

        password.setText(userDTO.getPassword());
        password.setDisable(isFormDisable);

        if (phones.size() == 1) {
            phone1.setText(phones.get(0));
            phone1.setDisable(isFormDisable);
        } else if (phones.size() == 2) {
            phone1.setText(phones.get(0));
            phone2.setText(phones.get(1));

            phone1.setDisable(isFormDisable);
            phone2.setDisable(isFormDisable);
        }

        userDTO.getRoles()
                .forEach(authority -> rolesCheckList
                        .getSelectionModel()
                        .selectItem(authority));

        rolesCheckList.setDisable(isFormDisable);
        rolesCheckList.setMinHeight(140);

        isUserActive.setSelected(userDTO.isActive());
        isUserActive.setDisable(isFormDisable);

        submitButton.setVisible(!isFormDisable);
    }

    private void validateRequiredFields(MFXButton submitButton) {
        var requiredFields = List.of(name, email, password);
        Validators.validateFormSubmitButton(requiredFields, submitButton);
    }

}
