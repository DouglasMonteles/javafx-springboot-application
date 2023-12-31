package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormProductRegisterController;
import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.services.CategoryService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ProductController implements Initializable {

    @Autowired
    private CategoryService categoryService;

    private static CategoryDTO selectedCategory;

    private SubmitAction<CategoryDTO> submitAction;

    @FXML
    private MFXButton submitButton;

    @FXML
    private VBox insertProductContainer;

    @FXML
    private VBox updateProductContainer;

    @FXML
    private VBox infoProductContainer;

    @Value("${messages.insert_product.title}")
    private String insertProductTitle;

    @Value("${messages.insert_product.success}")
    private String insertProductSuccessMessage;

    @Value("${messages.insert_product.error}")
    private String insertProductErrorMessage;

    @Value("${messages.update_product.title}")
    private String updateProductTitle;

    @Value("${messages.update_product.success}")
    private String updateProductSuccessMessage;

    @Value("${messages.update_product.error}")
    private String updateProductErrorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertProductContainer != null) {
            var formInsertCategory = new FormProductRegisterController();
            formInsertCategory.setTitle("Cadastro de Categoria");
            formInsertCategory.setSubmitAction(this::insertCategory);

            insertProductContainer.getChildren().add(formInsertCategory);
        }

        if (updateProductContainer != null) {
            var formUpdateCategory = new FormProductRegisterController();
            formUpdateCategory.setTitle("Atualização da categoria");
            formUpdateCategory.setFormDisabled(false);
            formUpdateCategory.setSubmitAction(this::updateCategory);
            formUpdateCategory.setCategoryDTO(selectedCategory);

            updateProductContainer.getChildren().clear();
            updateProductContainer.getChildren().add(formUpdateCategory);
        }

        if (infoProductContainer != null) {
            var formInfoCategory = new FormProductRegisterController();
            formInfoCategory.setTitle("Informações sobre a categoria");
            formInfoCategory.setFormDisabled(true);
            formInfoCategory.setCategoryDTO(selectedCategory);

            infoProductContainer.getChildren().clear();
            infoProductContainer.getChildren().add(formInfoCategory);
        }
    }

    public static void setSelectedCategory(CategoryDTO selectedCategory) {
        ProductController.selectedCategory = selectedCategory;
    }

    @FXML
    public void submit(ActionEvent event) {
        submitAction.handleSubmit(selectedCategory);
    }

    private void updateCategory(CategoryDTO updateCategoryDTO) {
        updateCategoryDTO.setId(selectedCategory.getId());

        var categoryDTO = categoryService.update(updateCategoryDTO);

        boolean categoryExists = categoryDTO.getId() > 0;

        if (categoryExists) {
            categoryService.updateTableData();
            Dialog.infoDialog(updateProductTitle, updateProductSuccessMessage, String.format("Categoria %s atualizada com sucesso!", categoryDTO.getName()));
            Routes.UPDATE_CATEGORY.close();
        } else {
            Dialog.errorDialog(updateProductTitle, updateProductErrorMessage, defaultErrorMessage);
        }
    }

    private void insertCategory(CategoryDTO categoryDTO) {
        var newCategory = this.categoryService.insert(categoryDTO);

        var isRegisteredCategory = newCategory.getId() > 0;

        if (isRegisteredCategory) {
            categoryService.updateTableData();
            Dialog.infoDialog(insertProductTitle, insertProductSuccessMessage, "Categoria " + newCategory.getName() + " cadastrada!");
            Routes.INSERT_CATEGORY.close();
        } else {
            Dialog.errorDialog(insertProductTitle, insertProductErrorMessage, defaultErrorMessage);
        }
    }

}
