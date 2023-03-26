package com.doug.jfx.store.controllers;

import com.doug.jfx.store.models.dtos.CategoryDTO;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CategoryController implements Initializable {

    private static CategoryDTO selectedCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setSelectedCategory(CategoryDTO selectedCategory) {
        CategoryController.selectedCategory = selectedCategory;
    }

}
