package com.shopping.controller;

import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {
  
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  
  @GetMapping(value = "/new")
  public String memberForm(Model model) {
    model.addAttribute("memberFormDto", new MemberFormDto());
    return "member/signIn";
  }
  
  // @PostMapping(value = "/new")
  // public String memberForm(MemberFormDto memberFormDto) {
  // Member member = Member.createMember(memberFormDto, passwordEncoder);
  // memberService.saveMember(member);
  // return "redirect:/";
  // }
  
  // 검증하려는 객체 앞에 @Valid 선언, 파라미터로 bindingResult 객체 추가
  @PostMapping(value = "/new")
  public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,
      Model model) {
    // 유효성 검사 결과는 bindingResult에 담김
    if (bindingResult.hasErrors()) {
      return "member/signIn";
    }
    
    try {
      Member member = Member.createMember(memberFormDto, passwordEncoder);
      memberService.saveMember(member);
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "member/signIn";
    }
    
    return "redirect:/";
  }
  
  @GetMapping(value = "/login")
  public String loginMember() {
    return "/member/login";
  }
  
  @GetMapping(value = "/login/error")
  public String loginError(Model model) {
    model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
    return "/member/login";
  }
  
}
