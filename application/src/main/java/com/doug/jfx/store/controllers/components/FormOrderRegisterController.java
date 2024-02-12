package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.services.OrderService;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class FormOrderRegisterController extends VBox implements Initializable {

    private final OrderService orderService;

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

    private OrderDTO orderDTO;

    private boolean isFormDisabled = false;

    public FormOrderRegisterController(OrderService orderService) {
        this.orderDTO = new OrderDTO();
        this.orderService = orderService;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/components/form_order_component.fxml"));
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

    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setProductDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
        this.populateFormWithOrderData();
    }

    public boolean isFormDisabled() {
        return isFormDisabled;
    }

    public void setFormDisabled(boolean formDisabled) {
        isFormDisabled = formDisabled;
    }

    private void populateFormWithOrderData() {
        boolean isFormDisable = isFormDisabled();

    }

}
