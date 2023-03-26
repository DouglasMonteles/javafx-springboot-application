package com.doug.jfx.store.services;

import com.doug.jfx.store.models.dtos.CategoryDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    TableView<?> buildCategoryTable();

    void updateTableData();

}
