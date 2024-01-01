package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.repositories.CategoryRepository;
import com.doug.jfx.store.repositories.ProductRepository;
import com.doug.jfx.store.services.ProductService;
import jakarta.transaction.Transactional;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final TableBuilder tableBuilder = new TableBuilderImpl<ProductDTO>();

    @Override
    public List<ProductDTO> findAll() {
        var products = productRepository.findAll();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        var product = new Product(productDTO);
        product.setId(null);

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        var product = new Product(productDTO);

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
            Dialog.infoDialog("Exclusão de categoria", "Categoria excluída com sucesso!", "Esta categoria não consta mais no sistema");
        } catch (DataIntegrityViolationException e) {
            Dialog.errorDialog("Exclusão de categoria", "Erro ao tentar excluir a categoria", "Erro de integridade de dados: Você está tentando excluir uma categoria que possui produtos vinculados a ela. \n\nDica: Desvincule estes produtos e tente novamente.");
        }
    }

    @Override
    public TableView<?> buildProductTable() {
        var products = findAll();

        return tableBuilder
                .addColumn("Id", "id")
                .addColumn("Nome", "name")
                .addColumn("Preço", "price")
                .setData(products)
                .build();
    }

    @Override
    public void updateTableData() {
        var products = findAll();
        tableBuilder.setData(products);
    }

}
