package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
  
  Member findByEmail(String email);  // 중복된 회원 유무 검사
  
}
