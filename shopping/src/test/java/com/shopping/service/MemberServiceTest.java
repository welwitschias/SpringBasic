package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {
  
  @Autowired
  MemberService memberService;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  public Member createMember() {
    MemberFormDto memberFormDto = new MemberFormDto();
    memberFormDto.setEmail("test@email.com");
    memberFormDto.setName("홍길동");
    memberFormDto.setAddress("부산");
    memberFormDto.setPassword("1234");
    return Member.createMember(memberFormDto, passwordEncoder);
  }
  
  @Test
  @DisplayName("회원가입 테스트")
  public void saveMemberTest() {
    Member member = createMember();
    Member savedMember = memberService.saveMember(member);
    
    // assertEquals : 저장하려고 요청했던 값과 실제 저장된 데이터를 비교
    // 첫 번째 파라미터에는 기대값, 두 번째 파라미터에는 실제로 저장된 값
    assertEquals(member.getEmail(), savedMember.getEmail());
    assertEquals(member.getName(), savedMember.getName());
    assertEquals(member.getAddress(), savedMember.getAddress());
    assertEquals(member.getPassword(), savedMember.getPassword());
    assertEquals(member.getRole(), savedMember.getRole());
  }
  
  @Test
  @DisplayName("중복회원 가입 테스트")
  public void saveDuplicateMemberTest() {
    Member member1 = createMember();
    Member member2 = createMember();
    memberService.saveMember(member1);
    
    // assertThrows : 예외 처리 테스트 가능
    // 첫 번째 파라미터에는 발생할 예외 타입
    Throwable e = assertThrows(IllegalStateException.class, () -> {
      memberService.saveMember(member2);
    });
    
    assertEquals("이미 가입된 회원입니다.", e.getMessage());
  }
  
}
