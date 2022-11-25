package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.shopping.constant.Role;
import com.shopping.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
  
  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String name;
  
  // 동일한 값이 데이터베이스에 들어올 수 없도록 unique 속성 지정
  @Column(unique = true)
  private String email;
  
  private String password;
  
  private String address;
  
  // enum 타입을 entity의 속성으로 지정
  // enum은 기본적으로 순서로 저장되는데 만약 순서가 바뀔 경우 문제가 발생할 수 있으므로 String으로 저장
  @Enumerated(EnumType.STRING)
  private Role role;
  
  public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
    Member member = new Member();
    member.setName(memberFormDto.getName());
    member.setEmail(memberFormDto.getEmail());
    member.setAddress(memberFormDto.getAddress());
    
    // BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호를 암호화
    String password = passwordEncoder.encode(memberFormDto.getPassword());
    member.setPassword(password);
    member.setRole(Role.USER);
    
    return member;
  }
  
}
