package com.example.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membership_user")
public class MembershipUser {
  
  @Id
  private String email;
  
  private String name;
  private Date   createdDate;
  
  // 양방향(1:1) 관계 설정
  @OneToOne(mappedBy = "owner") // 즉시 전략, owner를 바라만 본다...
  private MembershipCard card;
  
}
