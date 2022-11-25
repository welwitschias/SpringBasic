package com.shopping.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
  
  private Long id;
  
  private String itemName;
  
  private int price;
  
  private int stockNumber;
  
  private String itemDetail;
  
  private String itemSellStatus;
  
  private LocalDateTime registerTime;
  
  private LocalDateTime updateTime;
  
}
