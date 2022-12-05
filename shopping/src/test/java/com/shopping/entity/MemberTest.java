package com.shopping.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.repository.MemberRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {
  
  @Autowired
  MemberRepository memberRepository;
  
  @PersistenceContext
  EntityManager em;
  
  @Test
  @DisplayName("Auditing 테스트")
  @WithMockUser(username = "gildong", roles = "USER") // WithMockUser에 지정한 사용자가 로그인한 상태라고 가정하고 테스트 진행
  public void auditingTest() {
    Member newMember = new Member();
    memberRepository.save(newMember);
    
    em.flush();
    em.clear();
    
    Member member =
        memberRepository.findById(newMember.getId()).orElseThrow(EntityNotFoundException::new);
    
    System.out.println("register time : " + member.getRegisterTime());
    System.out.println("update time : " + member.getUpdateTime());
    System.out.println("create member : " + member.getCreatedBy());
    System.out.println("modify member : " + member.getModifiedBy());
  }
  
}
