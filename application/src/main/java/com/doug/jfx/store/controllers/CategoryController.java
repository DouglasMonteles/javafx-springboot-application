package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CategoryController implements Initializable {

    private static CategoryDTO selectedCategory;

    private SubmitAction submitAction;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXButton submitButton;

    @FXML
    private Text title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setSelectedCategory(CategoryDTO selectedCategory) {
        CategoryController.selectedCategory = selectedCategory;
    }

    @FXML
    void submit(ActionEvent event) {
        submitAction.handleSubmit(selectedCategory);
    }

}
