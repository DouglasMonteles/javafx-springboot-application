package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.enums.PictureType;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Picture;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.PictureDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.repositories.CategoryRepository;
import com.doug.jfx.store.repositories.PictureRepository;
import com.doug.jfx.store.repositories.ProductRepository;
import com.doug.jfx.store.services.ProductService;
import com.doug.jfx.store.services.UploadFileService;
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

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private PictureRepository pictureRepository;

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

        for (PictureDTO pictureDTO : productDTO.getPictures()) {
            String path = "/upload/images/products/prod" + product.getId() + "-";
            String finalPath = uploadFileService.uploadFile(pictureDTO.getPicture(), path);

            pictureDTO.setPath(finalPath);
            pictureDTO.setType(PictureType.URL);
            pictureDTO.setProduct(product);

            pictureRepository.save(new Picture(pictureDTO));
        }

        return new ProductDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        var product = new Product(productDTO);

        product = productRepository.save(product);

        for (PictureDTO pictureDTO : productDTO.getPictures()) {
            if (pictureDTO.getPicture() != null) {
                String path = "/upload/images/products/prod" + product.getId() + "-";
                String finalPath = uploadFileService.uploadFile(pictureDTO.getPicture(), path);

                if (finalPath != null) {
                    pictureDTO.setPath(finalPath);
                    pictureDTO.setType(PictureType.URL);
                    pictureDTO.setProduct(product);

                    pictureRepository.save(new Picture(pictureDTO));
                }
            }
        }

        return new ProductDTO(product);
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
            Dialog.infoDialog("Exclusão de produto", "Produto excluído com sucesso!", "Esta categoria não consta mais no sistema");
        } catch (DataIntegrityViolationException e) {
            Dialog.errorDialog("Exclusão de produto", "Erro ao tentar excluir o produto", "Erro de integridade de dados: Você está tentando excluir uma produto que possui outras entidades vinculados a ele (Pedido, Categoria, etc.). \n\nDica: Desvincule estas entidades e tente novamente.");
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
