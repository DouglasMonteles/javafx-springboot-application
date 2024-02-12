package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.OrderedItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, OrderedItemPK> {


    @Query("SELECT orderItem FROM OrderedItem orderItem WHERE orderItem.id.order.id = :orderId")
    List<OrderedItem> findOrderedItemsByOrderId(Long orderId);

}
