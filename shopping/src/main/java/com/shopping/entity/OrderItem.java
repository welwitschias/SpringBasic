package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {
  
  @Id
  @GeneratedValue
  @Column(name = "order_item_id")
  private Long id;
  
  // item은 여러 번 주문될 수 있으므로 order_item 기준에서 다대일 단방향 mapping
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;
  
  // order 한 번에 여러 개의 item을 주문할 수 있으므로 order_item 기준에서 다대일 단방향 mapping
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;
  
  private int orderPrice;
  
  private int count;
  
}
