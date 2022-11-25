package com.shopping.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    
    // ajax는 http request header에 XMLHttpRequest값이 세팅되어 요청
    // 인증되지 않은 사용자가 ajax로 리소스를 요청할 경우 "Unauthorized" 에러 발생시킴
    // 나머지 경우는 로그인 페이지로 이동
    if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    } else {
      response.sendRedirect("/members/login");
    }
    
  }
  
}
