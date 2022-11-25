package com.example.demo.model;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {
  
  @Id // 기본키로 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long   id;  // Long 해야 번호 자동증가 가능
  private String name;
  
  @Enumerated(EnumType.STRING)
  private Grade grade;
  
  @Embedded
  private Address address;
  
  @OneToMany(mappedBy = "hotel")
  private List<HotelReview> reviews;
  
}
