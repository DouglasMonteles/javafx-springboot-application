package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.OrderedItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, OrderedItemPK> {
}
