package com.shopping.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;
import com.shopping.service.MemberService;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {
  
  @Autowired
  private MemberService memberService;
  
  // 테스트에 필요한 기능만 가지는 가짜 객체
  // 웹 브라우저에 요청하는 것처럼 테스트 가능
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  public Member createMember(String email, String password) {
    MemberFormDto memberFormDto = new MemberFormDto();
    memberFormDto.setEmail(email);
    memberFormDto.setName("홍길동");
    memberFormDto.setAddress("부산");
    memberFormDto.setPassword(password);
    
    Member member = Member.createMember(memberFormDto, passwordEncoder);
    return memberService.saveMember(member);
  }
  
  @Test
  @DisplayName("로그인 성공 테스트")
  public void loginSuccessTest() throws Exception {
    String email = "test@email.com";
    String password = "test1234";
    this.createMember(email, password);
    
    mockMvc.perform(formLogin().userParameter("email")
           .loginProcessingUrl("/members/login")
           .user(email).password(password))
           .andExpect(SecurityMockMvcResultMatchers.authenticated()); // 로그인 성공하여 인증되면 테스트 코드 통과
  }
  
  @Test
  @DisplayName("로그인 실패 테스트")
  public void loginFailTest() throws Exception {
    String email = "test@email.com";
    String password = "test1234";
    this.createMember(email, password);
    
    mockMvc.perform(formLogin().userParameter("email")
           .loginProcessingUrl("/members/login")
           .user(email).password("1234"))
           .andExpect(SecurityMockMvcResultMatchers.unauthenticated()); // 로그인 실패하면 테스트 코드 통과
  }
  
}
