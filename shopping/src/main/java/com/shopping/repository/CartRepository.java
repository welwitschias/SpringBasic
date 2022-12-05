package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
  
}
