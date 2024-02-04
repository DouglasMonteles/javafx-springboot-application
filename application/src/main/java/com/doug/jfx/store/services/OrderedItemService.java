package com.doug.jfx.store.services;

import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.dtos.OrderDTO;
import javafx.scene.control.TableView;

import java.util.List;

public interface OrderedItemService {

    void addCartItem(OrderedItem orderedItem);

    void removeCartItem(int index);

    List<OrderedItem> getCartItems();

}
