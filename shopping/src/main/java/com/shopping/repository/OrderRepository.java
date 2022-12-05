package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  
}
