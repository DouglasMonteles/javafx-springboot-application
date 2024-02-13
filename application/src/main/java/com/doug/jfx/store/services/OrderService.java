package com.doug.jfx.store.services;

import com.doug.jfx.store.models.dtos.OrderDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO insert(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    void confirmOrderPayment(Long orderId);

    void cancelOrder(Long orderId);

    void delete(Long id);

    TableView<?> buildOrderTable();

    void updateTableData();

}
