package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membership_card")
public class MembershipCard {
  
  @Id
  @Column(name = "card_number")
  private String cardNumber;
  
  private Date    expiryDate;
  private boolean enabled;
  
  // 양방향(1:1) 관계 설정
  @OneToOne // 즉시 전략
  @JoinColumn(name = "user_email") // 외래키 설정, 외래키를 가진 쪽이 주인이다.
  private MembershipUser owner;
  
}
