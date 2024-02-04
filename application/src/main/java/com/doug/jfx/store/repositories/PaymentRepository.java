package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
