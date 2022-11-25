package com.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.shopping.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Autowired
  MemberService memberservice;
  
  // http 요청에 대한 보안 설정 : 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // csrf 토큰 생성
    
    http.formLogin()
        .loginPage("/members/login") // 로그인 페이지 URL
        .defaultSuccessUrl("/") // 로그인 성공 시 이동할 페이지 URL
        .usernameParameter("email") // 로그인 시 사용할 파라미터 이름
        .failureUrl("/members/login/error") // 로그인 실패 시 이동할 페이지 URL
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL
        .logoutSuccessUrl("/"); // 로그아웃 성공 시 이동할 페이지 URL
    
    return http.build();
  }
  
  // BCryptPasswordEncoder : 해시 함수를 이용하여 비밀번호를 암호화하여 데이터베이스에 저장
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(memberservice)
        .passwordEncoder(passwordEncoder());
  }
  
}
