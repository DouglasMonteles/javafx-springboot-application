package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormCategoryRegisterController;
import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.services.CategoryService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CategoryController implements Initializable {

    @Autowired
    private CategoryService categoryService;

    private static CategoryDTO selectedCategory;

    private SubmitAction<CategoryDTO> submitAction;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXButton submitButton;

    @FXML
    private Text title;

    @FXML
    private VBox insertCategoryContainer;

    @Value("${messages.insert_category.title}")
    private String insertCategoryTitle;

    @Value("${messages.insert_category.success}")
    private String insertCategorySuccessMessage;

    @Value("${messages.insert_category.error}")
    private String insertCategoryErrorMessage;

    @Value("${messages.update_category.title}")
    private String updateCategoryTitle;

    @Value("${messages.update_category.success}")
    private String updateCategorySuccessMessage;

    @Value("${messages.update_category.error}")
    private String updateCategoryErrorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertCategoryContainer != null) {
            var formInsertCategory = new FormCategoryRegisterController();
            formInsertCategory.setTitle("Cadastro de Categoria");
            formInsertCategory.setSubmitAction(this::insertCategory);

            insertCategoryContainer.getChildren().add(formInsertCategory);
        }
    }

    public static void setSelectedCategory(CategoryDTO selectedCategory) {
        CategoryController.selectedCategory = selectedCategory;
    }

    @FXML
    public void submit(ActionEvent event) {
        submitAction.handleSubmit(selectedCategory);
    }

    private void insertCategory(CategoryDTO categoryDTO) {
        var newCategory = this.categoryService.insert(categoryDTO);

        var isRegisteredCategory = newCategory.getId() > 0;

        if (isRegisteredCategory) {
            categoryService.updateTableData();
            Dialog.infoDialog(insertCategoryTitle, insertCategorySuccessMessage, "Categoria " + newCategory.getName() + " cadastrada!");
            Routes.INSERT_CATEGORY.close();
        } else {
            Dialog.errorDialog(insertCategoryTitle, insertCategoryErrorMessage, defaultErrorMessage);
        }
    }

}
