package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JpaMember {
  
  @Id // 기본키 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  private String password;
  private String email;
  private String memo;
  
  @Column(name = "address") // 이름 바꾸기
  private String addr;
  
}
