package com.doug.jfx.store.controllers;

import com.doug.jfx.store.controllers.components.FormOrderRegisterController;
import com.doug.jfx.store.controllers.components.FormProductRegisterController;
import com.doug.jfx.store.controllers.components.SubmitAction;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.models.dtos.OrderListTableDTO;
import com.doug.jfx.store.services.CategoryService;
import com.doug.jfx.store.services.OrderService;
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
public class OrderController implements Initializable {

    @Autowired
    private OrderService orderService;

    private static OrderListTableDTO selectedOrder;

    private SubmitAction<OrderListTableDTO> submitAction;

    @FXML
    public VBox infoOrderContainer;

    @FXML
    private MFXButton submitButton;

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
        if (infoOrderContainer != null) {
            var formInfoOrder = new FormOrderRegisterController(orderService);
            //formInfoCategory.setTitle("Informações sobre a categoria");
            formInfoOrder.setFormDisabled(true);
            formInfoOrder.setOrderDTO(selectedOrder);

            infoOrderContainer.getChildren().clear();
            infoOrderContainer.getChildren().add(formInfoOrder);
        }
    }

    public static void setSelectedOrder(OrderListTableDTO selectedOrder) {
        OrderController.selectedOrder = selectedOrder;
    }

    @FXML
    public void submit(ActionEvent event) {
        submitAction.handleSubmit(selectedOrder);
    }

}
