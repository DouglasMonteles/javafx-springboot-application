package com.doug.jfx.store.controllers.components;

import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.models.dtos.OrderListTableDTO;
import com.doug.jfx.store.services.OrderService;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
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

    private OrderListTableDTO orderDTO;

    private boolean isFormDisabled = false;

    @FXML
    private MFXTextField date;

    @FXML
    private MFXTextField id;

    @FXML
    private MFXScrollPane items;

    @FXML
    private MFXTextField paymentMethod;

    @FXML
    private MFXTextField seller;

    @FXML
    private MFXTextField status;

    @FXML
    private MFXTextField total;

    public FormOrderRegisterController(OrderService orderService) {
        this.orderDTO = new OrderListTableDTO();
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

    public void setOrderDTO(OrderListTableDTO orderDTO) {
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
        boolean isAllowEdit = !isFormDisable;

        id.setText(orderDTO.getId().toString());
        id.setAllowEdit(isAllowEdit);

        date.setText(orderDTO.getDate());
        date.setAllowEdit(isAllowEdit);

        paymentMethod.setText(orderDTO.getPayment());
        paymentMethod.setAllowEdit(isAllowEdit);

        seller.setText(orderDTO.getClientName());
        seller.setAllowEdit(isAllowEdit);

        status.setText(orderDTO.getStatus());
        status.setAllowEdit(isAllowEdit);

        total.setText(orderDTO.getTotal());
        total.setAllowEdit(isAllowEdit);

        MFXListView<String> listOrderedItems = new MFXListView<>();
        listOrderedItems.setPrefWidth(600);
        listOrderedItems.setItems(FXCollections.observableArrayList(orderDTO.getOrderedItems()));

        items.setContent(listOrderedItems);
    }

}
