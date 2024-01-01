package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormProductRegisterController;
import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.enums.Routes;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.services.CategoryService;
import com.doug.jfx.store.services.ProductService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProductController implements Initializable {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private static ProductDTO selectedProduct;

    private SubmitAction<ProductDTO> submitAction;

    @FXML
    private MFXButton submitButton;

    @FXML
    private VBox insertProductContainer;

    @FXML
    private VBox updateProductContainer;

    @FXML
    private VBox infoProductContainer;

    @Value("${messages.insert_product.title}")
    private String insertProductTitle;

    @Value("${messages.insert_product.success}")
    private String insertProductSuccessMessage;

    @Value("${messages.insert_product.error}")
    private String insertProductErrorMessage;

    @Value("${messages.update_product.title}")
    private String updateProductTitle;

    @Value("${messages.update_product.success}")
    private String updateProductSuccessMessage;

    @Value("${messages.update_product.error}")
    private String updateProductErrorMessage;

    @Value("${messages.default.error}")
    private String defaultErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertProductContainer != null) {
            var formInsertCategory = new FormProductRegisterController(productService, categoryService);
            formInsertCategory.setTitle("Cadastro de Produto");
            formInsertCategory.setSubmitAction(this::insertProduct);

            insertProductContainer.getChildren().add(formInsertCategory);
        }

        if (updateProductContainer != null) {
            var formUpdateCategory = new FormProductRegisterController(productService, categoryService);
            formUpdateCategory.setTitle("Atualização da categoria");
            formUpdateCategory.setFormDisabled(false);
            formUpdateCategory.setSubmitAction(this::updateProduct);
            formUpdateCategory.setProductDTO(selectedProduct);

            updateProductContainer.getChildren().clear();
            updateProductContainer.getChildren().add(formUpdateCategory);
        }

        if (infoProductContainer != null) {
            var formInfoCategory = new FormProductRegisterController(productService, categoryService);
            formInfoCategory.setTitle("Informações sobre a categoria");
            formInfoCategory.setFormDisabled(true);
            formInfoCategory.setProductDTO(selectedProduct);

            infoProductContainer.getChildren().clear();
            infoProductContainer.getChildren().add(formInfoCategory);
        }
    }

    public static void setSelectedProduct(ProductDTO selectedProduct) {
        ProductController.selectedProduct = selectedProduct;
    }

    @FXML
    public void submit(ActionEvent event) {
        submitAction.handleSubmit(selectedProduct);
    }

    private void updateProduct(ProductDTO updateProductDTO) {
        updateProductDTO.setId(selectedProduct.getId());

        var productDTO = productService.update(updateProductDTO);

        boolean categoryExists = productDTO.getId() > 0;

        if (categoryExists) {
            productService.updateTableData();
            Dialog.infoDialog(updateProductTitle, updateProductSuccessMessage, String.format("Categoria %s atualizada com sucesso!", productDTO.getName()));
            Routes.UPDATE_CATEGORY.close();
        } else {
            Dialog.errorDialog(updateProductTitle, updateProductErrorMessage, defaultErrorMessage);
        }
    }

    private void insertProduct(ProductDTO productDTO) {
        var newProduct = this.productService.insert(productDTO);

        var isRegisteredProduct = newProduct.getId() > 0;

        if (isRegisteredProduct) {
            productService.updateTableData();
            Dialog.infoDialog(insertProductTitle, insertProductSuccessMessage, "Produto " + newProduct.getName() + " cadastrado!");
            Routes.INSERT_PRODUCT.close();
        } else {
            Dialog.errorDialog(insertProductTitle, insertProductErrorMessage, defaultErrorMessage);
        }
    }

}
