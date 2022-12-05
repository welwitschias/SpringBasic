package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart {
  
  @Id
  @Column(name = "cart_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @OneToOne(fetch = FetchType.LAZY) // 일대일 mapping에서 즉시 로딩(EAGER)이 기본값, 지연 로딩(LAZY)
  @JoinColumn(name = "member_id") // mapping할 외래키의 이름 설정
  private Member member;
  
}
