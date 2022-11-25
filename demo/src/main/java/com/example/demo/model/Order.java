package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "apple_order")
public class Order {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long   orderid;
  private String note;
  private String ordername;
  private int    price;
  
  // ManyToOne : 즉시 전략
  // LAZY : 지연 전략, EAGER : 즉시 전략
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id") // 외래키를 가진 쪽(다수)이 주인이다.
  private User user;
  
}
