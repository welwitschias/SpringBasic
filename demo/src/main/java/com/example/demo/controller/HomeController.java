package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.JpaMember;
import com.example.demo.service.MemberService;

@Controller
public class HomeController {
  
  // 객체 자동 주입
  @Autowired
  private MemberService memberService;
  
  @GetMapping("/")
  public String home() {
    return "home";
  }
  
  @GetMapping("/join")
  public String join() {
    return "join";
  }
  
  @PostMapping("/join")
  public String join(JpaMember member) {
    memberService.save(member);
    // redirect로 관계를 끊어줌(null값 계속 안 들어가도록)
    return "redirect:/";
  }
  
  // 멤버 조회(전체보기, 페이징 없음)
  // @GetMapping("/list")
  // public String list(Model model) {
  // List<JpaMember> members = memberService.list();
  // model.addAttribute("lists", members);
  // return "list";
  // }
  
  // 멤버 조회(전체보기, 페이징 있음)
  @GetMapping("/list")
  public String list(Model model,
      @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    // System.out.println("pageable :" + pageable);
    Page<JpaMember> lists = memberService.list(pageable);
    model.addAttribute("lists", lists);
    return "list";
  }
  
  // 멤버 조회(상세보기)
  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Long id, Model model) {
    model.addAttribute("member", memberService.detail(id));
    return "detail";
  }
  
  // 멤버 조회(상세보기_ajax)
  @ResponseBody
  @GetMapping("/detail2/{id}")
  public JpaMember detail2(@PathVariable Long id) {
    return memberService.detail(id);
  }
  
  // 멤버 삭제
  // mapping 주소와 return 주소가 같으면 void 사용해도 됨
  // @RestController : 데이터만 넘기는 클래스를 사용할 때 사용(최상단에 표시)
  @ResponseBody // 뷰가 아니라 데이터를 넘길 때 사용(메소드마다)
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable Long id) {
    memberService.delete(id);
    return id;
  }
  
  // 멤버 수정화면으로
  @GetMapping("/update/{id}")
  public String update(@PathVariable Long id, Model model) {
    model.addAttribute("member", memberService.detail(id));
    return "update";
  }
  
  // 멤버 수정
  // @RequestBody : (form 태그가 아닌) json 형태의 데이터를 받아옴
  @ResponseBody
  @PutMapping("update")
  public String update(@RequestBody JpaMember member) {
    memberService.update(member);
    return "success";
  }
  
}
