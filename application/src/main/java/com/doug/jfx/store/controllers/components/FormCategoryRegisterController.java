package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.builders.CategoryBuilder;
import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.builders.impl.CategoryBuilderImpl;
import com.doug.jfx.store.builders.impl.UserBuilderImpl;
import com.doug.jfx.store.helpers.Validators;
import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.dtos.CategoryDTO;
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
public class FormCategoryRegisterController extends VBox implements Initializable {

    @FXML
    private Text title;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXButton submitButton;

    private SubmitAction<CategoryDTO> submitAction;

    private CategoryDTO categoryDTO;

    private boolean isFormDisabled = false;

    public FormCategoryRegisterController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/form_category_component.fxml"));
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
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSubmitAction(SubmitAction<CategoryDTO> submitAction) {
        this.submitAction = submitAction;
    }

    public SubmitAction<CategoryDTO> getSubmitAction() {
        return submitAction;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        this.populateFormWithCategoryData();
    }

    public boolean isFormDisabled() {
        return isFormDisabled;
    }

    public void setFormDisabled(boolean formDisabled) {
        isFormDisabled = formDisabled;
    }

    @FXML
    private void submit() {
        CategoryBuilder categoryBuilder = new CategoryBuilderImpl();

        categoryBuilder.setName(name.getText());

        var newCategoryDTO = categoryBuilder.buildAndConvertToDTO();

        getSubmitAction().handleSubmit(newCategoryDTO);
    }

    private void populateFormWithCategoryData() {
        boolean isFormDisable = isFormDisabled();

        submitButton.setText("Atualizar categoria");

        name.setText(categoryDTO.getName());
        name.setDisable(isFormDisable);

        submitButton.setVisible(!isFormDisable);
    }

    private void validateRequiredFields(MFXButton submitButton) {
        var requiredFields = List.of(name);
        Validators.validateFormSubmitButton(requiredFields, submitButton);
    }

}
