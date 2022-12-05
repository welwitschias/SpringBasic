package com.shopping.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.shopping.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders") // 정렬할 때 사용하는 order 키워드가 존재하므로 orders로 설정
@Getter
@Setter
public class Order extends BaseEntity {
  
  @Id
  @GeneratedValue
  @Column(name = "order_id")
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY) // 회원은 여러 번 주문할 수 있으므로 order 기준에서 다대일 단방향 mapping
  @JoinColumn(name = "member_id")
  private Member member;
  
  private LocalDateTime orderDate;
  
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  
  // 외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티
  // mappedBy 속성으로 연관관계의 주인 설정
  // order는 OrderItem에 있는 order
  // 하나의 order가 여러 개의 order_item을 가지므로 List 사용
  // CascadeType.ALL은 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이
  // orphanRemoval=true는 고아 객체 제거
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<>();
  
}
