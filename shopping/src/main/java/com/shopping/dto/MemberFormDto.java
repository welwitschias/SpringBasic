package com.shopping.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {
  
  // NotBlank : Null 체크, 문자열 길이 0인지 검사, 빈 문자열(" ") 검사
  // NotEmpty : Null 체크, 문자열 길이 0인지 검사
  
  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String name;
  
  @NotEmpty(message = "이메일은 필수 입력 값입니다.")
  @Email(message = "이메일 형식으로 입력해주세요.")
  private String email;
  
  @NotEmpty(message = "이메일은 필수 입력 값입니다.")
  @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
  private String password;
  
  @NotEmpty(message = "주소는 필수 입력 값입니다.")
  private String address;
  
}
