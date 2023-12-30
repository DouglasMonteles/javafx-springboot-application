package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.repositories.CategoryRepository;
import com.doug.jfx.store.services.CategoryService;
import jakarta.transaction.Transactional;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        var category = new Category(categoryDTO);
        category.setId(null);

        category = categoryRepository.save(category);

        return new CategoryDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO) {
        var category = new Category(categoryDTO);

        category = categoryRepository.save(category);

        return new CategoryDTO(category);
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
            Dialog.infoDialog("Exclusão de categoria", "Categoria excluída com sucesso!", "Esta categoria não consta mais no sistema");
        } catch (DataIntegrityViolationException e) {
            Dialog.errorDialog("Exclusão de categoria", "Erro ao tentar excluir a categoria", "Erro de integridade de dados: Você está tentando excluir uma categoria que possui produtos vinculados a ela. \n\nDica: Desvincule estes produtos e tente novamente.");
        }
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
