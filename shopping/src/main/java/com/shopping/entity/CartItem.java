package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {
  
  @Id
  @GeneratedValue
  @Column(name = "cart_item_id")
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id") // JoinColumn 어노테이션의 name으로 설정한 값이 외래키로 추가
  private Cart cart; // 하나의 장바구니에 여러 개의 상품을 담을 수 있음
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item; // 하나의 상품은 여러 개의 장바구니에 담길 수 있음
  
  private int count;
  
}
