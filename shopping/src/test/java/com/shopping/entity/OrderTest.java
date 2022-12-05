package com.shopping.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.constant.ItemSellStatus;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrderRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class OrderTest {
  
  @Autowired
  OrderRepository orderRepository;
  
  @Autowired
  ItemRepository itemRepository;
  
  @Autowired
  MemberRepository memberRepository;
  
  @Autowired
  OrderItemRepository orderItemRepository;
  
  @PersistenceContext
  EntityManager em;
  
  public Item createItem() {
    Item item = new Item();
    item.setItemName("테스트 상품");
    item.setPrice(10000);
    item.setItemDetail("테스트 상품 상세 설명");
    item.setItemSellStatus(ItemSellStatus.SELL);
    item.setStockNumber(100);
    item.setRegisterTime(LocalDateTime.now());
    item.setUpdateTime(LocalDateTime.now());
    return item;
  }
  
  @Test
  @DisplayName("영속성 전이 테스트")
  public void cascadeTest() {
    Order order = new Order();
    
    for (int i = 0; i < 3; i++) {
      Item item = this.createItem();
      itemRepository.save(item);
      
      OrderItem orderItem = new OrderItem();
      orderItem.setItem(item);
      orderItem.setCount(10);
      orderItem.setOrderPrice(1000);
      orderItem.setOrder(order);
      
      // 아직 영속성 컨텍스트에 저장되지 않은 orderItem 엔티티를 order 엔티티에 담아줌
      order.getOrderItems().add(orderItem);
    }
    
    orderRepository.saveAndFlush(order);
    em.clear();
    
    // 데이터베이스에서 order 엔티티를 조회
    Order savedOrder =
        orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
    
    // orderItem 엔티티 3개가 데이터베이스에 저장되었는지 검사
    assertEquals(3, savedOrder.getOrderItems().size());
  }
  
  public Order createOrder() {
    Order order = new Order();
    
    for (int i = 0; i < 3; i++) {
      Item item = createItem();
      itemRepository.save(item);
      
      OrderItem orderItem = new OrderItem();
      orderItem.setItem(item);
      orderItem.setCount(10);
      orderItem.setOrderPrice(1000);
      orderItem.setOrder(order);
      
      order.getOrderItems().add(orderItem);
    }
    
    Member member = new Member();
    memberRepository.save(member);
    
    order.setMember(member);
    orderRepository.save(order);
    return order;
  }
  
  @Test
  @DisplayName("고아객체 제거 테스트")
  public void orphanRemovalTest() {
    Order order = this.createOrder();
    order.getOrderItems().remove(0); // order 엔티티에서 관리하고 있는 orderItem 리스트의 0번째 인덱스 요소를 제거
    em.flush();
  }
  
  @Test
  @DisplayName("지연 로딩 테스트")
  public void lazyLoadingTest() {
    Order order = this.createOrder();
    Long orderItemId = order.getOrderItems().get(0).getId();
    
    em.flush();
    em.clear();
    
    OrderItem orderItem =
        orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
    System.out.println("Order class : " + orderItem.getOrder().getClass());
    System.out.println("==============================");
    orderItem.getOrder().getOrderDate();
    System.out.println("==============================");
  }
  
}
