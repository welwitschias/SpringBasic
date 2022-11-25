package com.example.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotel_review")
public class HotelReview {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long   id;
  private int    rate;
  private String comment;
  private Date   ratingDate;
  
  @ManyToOne // N:1 관계
  @JoinColumn(name = "hotel_id") // N:1에서 N쪽에 외래키를 설정, 외래키를 가진 쪽(다수)이 주인이다.
  private Hotel hotel;
  
}
