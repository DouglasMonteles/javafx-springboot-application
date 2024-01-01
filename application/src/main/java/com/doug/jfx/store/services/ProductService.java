package com.doug.jfx.store.services;

import com.doug.jfx.store.models.dtos.ProductDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO insert(ProductDTO categoryDTO);

    ProductDTO update(ProductDTO categoryDTO);

    void delete(Long id);

    TableView<?> buildProductTable();

    void updateTableData();

}
