package com.shopping.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import com.shopping.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item {
  
  @Id
  @Column(name = "item_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id; // 상품코드
  
  @Column(nullable = false, length = 50)
  private String itemName; // 상품명
  
  @Column(nullable = false)
  private int price; // 가격
  
  @Column(nullable = false)
  private int stockNumber; // 재고수량
  
  @Lob
  @Column(nullable = false)
  private String itemDetail; // 상품설명
  
  @Enumerated(EnumType.STRING)
  private ItemSellStatus itemSellStatus; // 판매상태
  
  private LocalDateTime registerTime; // 등록시간
  
  private LocalDateTime updateTime; // 수정시간
  
}
