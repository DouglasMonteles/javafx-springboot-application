package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.builders.TableBuilder;
import com.doug.jfx.store.builders.impl.TableBuilderImpl;
import com.doug.jfx.store.helpers.Dialog;
import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.repositories.OrderRepository;
import com.doug.jfx.store.repositories.OrderedItemRepository;
import com.doug.jfx.store.services.OrderService;
import com.doug.jfx.store.services.OrderedItemService;
import jakarta.transaction.Transactional;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private final List<OrderedItem> cartItems = new ArrayList<>();

    @Autowired
    private OrderedItemRepository orderedItemRepository;


    @Override
    public void addCartItem(OrderedItem orderedItem) {
        this.cartItems.add(orderedItem);
    }

    @Override
    public void removeCartItem(int index) {
        this.cartItems.remove(index);
    }

    @Override
    public List<OrderedItem> getCartItems() {
        return cartItems;
    }


}
