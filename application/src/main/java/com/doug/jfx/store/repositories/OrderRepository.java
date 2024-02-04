package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
