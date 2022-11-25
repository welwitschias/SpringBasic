package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.JpaMember;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Autowired와 같은 기능(객체 자동 주입)
@Service
public class MemberService {
  
  private final MemberRepository memberRepository;
  
  // 멤버 추가
  public void save(JpaMember member) {
    memberRepository.save(member);
  }
  
  // 멤버 조회(전체보기, 페이징 없음)
//  public List<JpaMember> list() {
//    return memberRepository.findAll();
//  }
  
  // 멤버 조회(전체보기, 페이징 있음)
  public Page<JpaMember> list(Pageable pageable) {
    return memberRepository.findAll(pageable);
  }
  
  // 멤버 조회(상세보기) - Optional 처리(null 처리하기)
  public JpaMember detail(Long id) {
    return memberRepository.findById(id).get();
    // .get()으로 갖고 있는 id값만 받아오기(비추천)
  }
  
  // 멤버 삭제
  public void delete(Long id) {
    memberRepository.deleteById(id);
  }
  
  // 멤버 수정(dirty checking, 변경 감지)
  // 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경
  // https://velog.io/@seongwon97/Spring-Boot-%EC%98%81%EC%86%8D%EC%84%B1-%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8Persistence-Context
  // jpa에 update란 sql은 존재하지 않음
  // flush() : db에 반영 → @Transactional 사용하면 flush(), commit() 한 번에 실행
  @Transactional
  public void update(JpaMember member) {
    JpaMember m = memberRepository.findById(member.getId()).get();
    m.setName(member.getName());
    m.setPassword(member.getPassword());
    m.setEmail(member.getEmail());
    m.setAddr(member.getAddr());
    m.setMemo(member.getMemo());
  }
  
}
