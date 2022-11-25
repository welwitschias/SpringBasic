package com.shopping.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.entity.Member;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // final이나 @NonNull이 붙은 필드에 생성자를 생성
public class MemberService implements UserDetailsService {
  
  private final MemberRepository memberRepository; // final이므로 @RequiredArgsConstructor 영향 받음
  
  public Member saveMember(Member member) {
    validateDuplicateMember(member);
    
    return memberRepository.save(member);
  }
  
  private void validateDuplicateMember(Member member) {
    Member findMember = memberRepository.findByEmail(member.getEmail());
    
    if (findMember != null) {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }
  
  // 로그인할 유저의 email을 파라미터로 전달 받음
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmail(email);
    
    if (member == null) {
      throw new UsernameNotFoundException(email);
    }
    
    // UserDetails를 구현하고 있는 User 객체 반환
    return User.builder()
          .username(member.getEmail())
          .password(member.getPassword())
          .roles(member.getRole().toString())
          .build();
  }
  
}
