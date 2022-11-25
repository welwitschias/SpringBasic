package com.shopping.repository;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.constant.ItemSellStatus;
import com.shopping.entity.Item;
import com.shopping.entity.QItem;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
  
  @Autowired
  ItemRepository itemRepository;
  
  @PersistenceContext
  EntityManager em;
  
  @Test
  @DisplayName("상품 저장 테스트")
  public void createItemTest() {
    Item item = new Item();
    item.setItemName("테스트 상품");
    item.setPrice(10000);
    item.setItemDetail("테스트 상품 상세 설명");
    item.setItemSellStatus(ItemSellStatus.SELL);
    item.setStockNumber(100);
    item.setRegisterTime(LocalDateTime.now());
    item.setUpdateTime(LocalDateTime.now());
    
    Item savedItem = itemRepository.save(item);
    System.out.println(savedItem.toString());
  }
  
  public void createItemList() {
    for (int i = 1; i <= 10; i++) {
      Item item = new Item();
      item.setItemName("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SELL);
      item.setStockNumber(100);
      item.setRegisterTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      
      Item savedItem = itemRepository.save(item);
      System.out.println(savedItem);
    }
  }
  
  @Test
  @DisplayName("상품명 조회 테스트")
  public void findByItemNameTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemName("테스트 상품1");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("상품명 OR 상품 상세 설명 테스트")
  public void findByItemNameOrItemDetailTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("가격 LESS THAN 테스트")
  public void findByPriceLessThanTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByPriceLessThan(10005);
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("가격 내림차순 조회 테스트")
  public void findByPriceLessThanOrderByPriceDescTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("@Query를 이용한 상품 조회 테스트")
  public void findByItemDetailTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
  public void findByItemDetailByNativeTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  @Test
  @DisplayName("Querydsl 조회 테스트1")
  public void queryDslTest() {
    this.createItemList();
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    
    // Querydsl 통해 쿼리를 생성하기 위해 QItem 객체 이용
    QItem qItem = QItem.item;
    JPAQuery<Item> query =
        queryFactory.selectFrom(qItem).where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
            .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%")).orderBy(qItem.price.desc());
    
    List<Item> itemList = query.fetch(); // fetch() : 쿼리 결과를 리스트로 반환
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }
  
  public void createItemList2() {
    for (int i = 1; i <= 5; i++) {
      Item item = new Item();
      item.setItemName("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SELL);
      item.setStockNumber(100);
      item.setRegisterTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      itemRepository.save(item);
    }
    
    for (int i = 6; i <= 10; i++) {
      Item item = new Item();
      item.setItemName("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
      item.setStockNumber(0);
      item.setRegisterTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      itemRepository.save(item);
    }
  }
  
  @Test
  @DisplayName("Querydsl 조회 테스트2")
  public void queryDslTest2() {
    this.createItemList2();
    
    // BooleanBuilder : 쿼리에 들어갈 조건을 만들어줌
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QItem qItem = QItem.item;
    
    String itemDetail = "테스트 상품 상세 설명";
    String itemSellStat = "SELL";
    int price = 10003;
    
    // 상품조회에 필요한 AND 조건 추가
    // 판매상태가 SELL일 때만 booleanBuilder에 판매상태 조건을 동적으로 추가
    booleanBuilder.and(qItem.itemDetail.like("%" + itemDetail + "%"));
    booleanBuilder.and(qItem.price.gt(price));
    if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
      booleanBuilder.and(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
    }
    
    // 데이터를 페이징해 조회하도록 PageRequest.of() 이용해 객체 생성
    // 첫 번째 인자는 조회할 페이지의 번호, 두 번째 인자는 한 페이지당 조회할 데이터의 갯수
    Pageable pageable = PageRequest.of(0, 5);
    Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
    System.out.println("total elements : " + itemPagingResult.getTotalElements());
    
    List<Item> resultItemList = itemPagingResult.getContent();
    for (Item resultItem : resultItemList) {
      System.out.println(resultItem.toString());
    }
  }
  
}
