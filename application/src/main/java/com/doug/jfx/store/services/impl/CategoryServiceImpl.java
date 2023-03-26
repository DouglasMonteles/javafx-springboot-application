package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.repositories.CategoryRepository;
import com.doug.jfx.store.services.CategoryService;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private final TableBuilder tableBuilder = new TableBuilderImpl<CategoryDTO>();

    @Override
    public List<CategoryDTO> findAll() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(CategoryDTO::new).toList();
    }

    @Override
    public TableView<?> buildCategoryTable() {
        var categories = findAll();

        return tableBuilder
                .addColumn("Id", "id")
                .addColumn("Nome", "name")
                .setData(categories)
                .build();
    }

    @Override
    public void updateTableData() {
        var categories = findAll();
        tableBuilder.setData(categories);
    }

}
