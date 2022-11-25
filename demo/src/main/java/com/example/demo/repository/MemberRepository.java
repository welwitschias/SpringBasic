package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.JpaMember;

// Long : JpaMember 기본키(@Id)의 type
public interface MemberRepository extends JpaRepository<JpaMember, Long> {
  
}
