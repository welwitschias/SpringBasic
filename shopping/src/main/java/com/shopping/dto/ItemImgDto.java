package com.shopping.dto;

import org.modelmapper.ModelMapper;
import com.shopping.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {
  
  private Long id;
  
  private String imgName;
  
  private String originalImgName;
  
  private String imgUrl;
  
  private String repImgYn;
  
  private static ModelMapper modelMapper = new ModelMapper();
  
  // ItemImg 객체의 자료형과 멤버변수의 이름이 같을 때, ItemImgDto로 값을 복사해서 반환
  // ItemImgDto 객체를 생성하지 않아도 호출할 수 있도록 static 선언
  public static ItemImgDto of(ItemImg itemImg) {
    return modelMapper.map(itemImg, ItemImgDto.class);
  }
  
}
