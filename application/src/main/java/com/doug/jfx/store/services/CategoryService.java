package com.doug.jfx.store.services;

import com.doug.jfx.store.models.dtos.CategoryDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO insert(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(Long id);

    TableView<?> buildCategoryTable();

    void updateTableData();

}
