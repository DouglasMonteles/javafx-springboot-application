package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.MoneyPayment;
import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.models.enums.PaymentStatus;
import com.doug.jfx.store.repositories.*;
import com.doug.jfx.store.services.OrderService;
import com.doug.jfx.store.services.OrderedItemService;
import com.doug.jfx.store.services.ProductService;
import jakarta.transaction.Transactional;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    private final TableBuilder tableBuilder = new TableBuilderImpl<OrderDTO>();

    @Override
    public List<OrderDTO> findAll() {
        var orders = orderRepository.findAll();
        return orders.stream().map(OrderDTO::new).toList();
    }

    @Override
    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        var order = new Order(orderDTO);
        order.setId(null);
        order.getPayment().setStatus(PaymentStatus.PENDING);
        order.getPayment().setOrder(order);

        order = orderRepository.save(order);
        paymentRepository.save(order.getPayment());

        for (OrderedItem item : order.getOrderedItems()) {
            var product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow();

            item.setOrder(order);
            item.setPrice(product.getPrice());
        }

        orderedItemRepository.saveAll(order.getOrderedItems());

        return new OrderDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO orderDTO) {
        var order = new Order(orderDTO);

        order = orderRepository.save(order);

        return new OrderDTO(order);
    }

    @Override
    public void delete(Long id) {
        try {
            orderRepository.deleteById(id);
            Dialog.infoDialog("Exclusão de pedido", "Pedido excluído com sucesso!", "Esta categoria não consta mais no sistema");
        } catch (DataIntegrityViolationException e) {
            Dialog.errorDialog("Exclusão de pedido", "Erro ao tentar excluir o pedido", "Erro de integridade de dados: Você está tentando excluir um pedido que possui produtos vinculados a ela. \n\nDica: Desvincule estes produtos e tente novamente.");
        }
    }

    @Override
    public TableView<?> buildOrderTable() {
        var orders = findAll();

        return tableBuilder
                .addColumn("Id", "id")
                .addColumn("Preço Total", "price")
                .setData(orders)
                .build();
    }

    @Override
    public void updateTableData() {
        var orders = findAll();
        tableBuilder.setData(orders);
    }

}
