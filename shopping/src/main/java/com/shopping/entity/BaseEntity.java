package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class}) // Auditing 적용
@MappedSuperclass // 공통 mapping 정보가 필요할 때 사용, 부모 클래스를 상속 받는 자식 클래스에 mapping 정보만 제공
@Getter
public abstract class BaseEntity extends BaseTimeEntity {
  
  @CreatedBy
  @Column(updatable = false)
  private String createdBy;
  
  @LastModifiedBy
  private String modifiedBy;
  
}
