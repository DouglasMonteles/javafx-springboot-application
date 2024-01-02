package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.builders.ProductBuilder;
import com.doug.jfx.store.builders.impl.ProductBuilderImpl;
import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.helpers.Validators;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;
import com.doug.jfx.store.services.CategoryService;
import com.doug.jfx.store.services.ProductService;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class FormProductRegisterController extends VBox implements Initializable {

    private final ProductService productService;

    private final CategoryService categoryService;

    @FXML
    private MFXTextField description;

    @FXML
    private MFXCheckbox isAvailable;

    @FXML
    private MFXTextField measurement;

    @FXML
    private MFXCheckListView<CategoryDTO> categories;

    @FXML
    private MFXFilterComboBox<ProductMeasurement> measurementType;

    @FXML
    private MFXButton picturesButton;

    @FXML
    private MFXScrollPane picturesPreviewContainer;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXTextField price;

    @FXML
    private Text title;

    @FXML
    private MFXButton submitButton;

    private SubmitAction<ProductDTO> submitAction;

    private ProductDTO productDTO;

    private boolean isFormDisabled = false;

    public FormProductRegisterController(ProductService productService,
                                         CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/form_product_component.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateRequiredFields(submitButton);
        submitButton.setDisable(true);

        measurementType.setItems(FXCollections.observableArrayList(
                List.of(ProductMeasurement.values())
        ));

        categories.setItems(FXCollections.observableArrayList(
                categoryService.findAll()
        ));
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSubmitAction(SubmitAction<ProductDTO> submitAction) {
        this.submitAction = submitAction;
    }

    public SubmitAction<ProductDTO> getSubmitAction() {
        return submitAction;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
        this.populateFormWithProductData();
    }

    public boolean isFormDisabled() {
        return isFormDisabled;
    }

    public void setFormDisabled(boolean formDisabled) {
        isFormDisabled = formDisabled;
    }

    @FXML
    void handleProductsPicturesUpload(ActionEvent event) {
        HBox container = new HBox();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma ou mais imagens...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png", "*.gif")
        );

        Stage stage = (Stage) picturesButton.getScene().getWindow();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            for (File picture : selectedFiles) {
                Image image = new Image(picture.toURI().toString());
                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageView.setCursor(Cursor.HAND);

                container.getChildren().add(imageView);

                long instant = System.currentTimeMillis();

                File destination = new File(System.getProperty("user.dir") + "/src/main/resources/upload/images/products/prod" + instant);

                try {
                    Files.copy(picture.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Erro no upload da imagem. Erro: " + e.getMessage());
                }
            }

            picturesPreviewContainer.setContent(container);
        }
    }

    @FXML
    private void submit() {
        ProductBuilder productBuilder = new ProductBuilderImpl();

        var newProductDTO = productBuilder
            .setId(null)
            .setName(name.getText())
            .setDescription(description.getText())
            .setPrice(new BigDecimal(price.getText()))
            .setMeasurementType(measurementType.getSelectedItem())
            .setMeasurement(Integer.parseInt(measurement.getText()))
            .setCategories(categories.getItems())
            .setPictures(List.of())
            .isAvailable(isAvailable.isSelected())
            .buildAndConvertToDTO();

        getSubmitAction().handleSubmit(newProductDTO);
    }

    private void populateFormWithProductData() {
        boolean isFormDisable = isFormDisabled();

        submitButton.setText("Atualizar Produto");

        name.setText(productDTO.getName());
        name.setDisable(isFormDisable);

        submitButton.setVisible(!isFormDisable);
    }

    private void validateRequiredFields(MFXButton submitButton) {
        var requiredFields = List.of(
                name,
                price,
                description
        );

        Validators.validateFormSubmitButton(requiredFields, submitButton);
    }

}
