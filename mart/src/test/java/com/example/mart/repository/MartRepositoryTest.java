package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.Delivery;
import com.example.mart.entity.Item;
import com.example.mart.entity.Member;
import com.example.mart.entity.Order;
import com.example.mart.entity.OrderItem;
import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void testMemberInsert() {
        IntStream.range(1, 5).forEach(i -> {
            Member member = Member.builder().name("user" + i).city("서울" + i).street("712" + i).zipcode("123" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testItemInsert() {
        IntStream.range(1, 5).forEach(i -> {
            Item item = Item.builder().name("티셔츠" + i).stockQueantity(i * 5).price(30000).build();
            itemRepository.save(item);
        });
    }

    @Test
    public void testOrderInsert() {
        Order order = Order.builder().member(Member.builder().id(1L).build()).orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER).build();
        orderRepository.save(order);
        // 주문 관련 상품은 OrderItem에
        OrderItem orderItem = OrderItem.builder().item(itemRepository.findById(2L).get()).order(order).orderPrice(28000)
                .count(1).build();
        orderItemRepository.save(orderItem);
    }

    @Transactional
    @Test
    public void testRead1() {
        Order order = orderRepository.findById(1L).get();
        System.out.println(order);
        System.out.println(order.getMember());

        orderRepository.findById(1L).get();
        System.out.println(order);
        System.out.println(order.getMember());
    }

    @Test
    public void testRead2() {

        Member member = memberRepository.findById(1L).get();
        System.out.println(member.getOrders());
    }

    @Test
    public void testRead3() {

        Member member = memberRepository.findById(1L).get();
        System.out.println(member.getOrders());
    }

    @Test
    public void testRead4() {

        Order order = orderRepository.findById(1L).get();
        order.getOrderItems().forEach(item -> System.out.println(item));
    }

    @Test
    public void testDelete1() {
        // 멤버 제거
        memberRepository.deleteById(1L);
    }

    @Test
    public void testDelete2() {
        // 주문상품 취소, 주문 취소, 멤버 제거
        orderItemRepository.deleteById(2L);
        orderRepository.deleteById(2L);
    }

    @Test
    public void testDelete3() {
        // 주문상품 취소, 주문 취소, 멤버 제거
        // 부모에 cascade
        orderRepository.deleteById(2L);
    }

    @Test
    @Transactional
    public void testDelete4() {
        Order order = orderRepository.findById(2L).get();
        order.getOrderItems().remove(0);
        orderRepository.save(order);
    }

    @Test
    public void testOrderInsert2() {
        // order 저장 시 orderitem 같이 저장
        Order order = Order.builder().member(Member.builder().id(1L).build()).orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER).build();
        // 주문 관련 상품은 OrderItem에
        OrderItem orderItem = OrderItem.builder().item(itemRepository.findById(2L).get()).order(order).orderPrice(28000)
                .count(1).build();
        order.getOrderItems().add(orderItem);
        orderRepository.save(order);
    }

    @Test
    public void testDeliveryInsert() {
        Delivery delivery = Delivery.builder().zipcode("1432").city("부산").street("123")
                .deliveryStatus(DeliveryStatus.READY).build();
        deliveryRepository.save(delivery);
        Order order = orderRepository.findById(1L).get();
        order.setDelivery(delivery);
    }

    @Test
    public void testDeliveryRead() {
        System.out.println(deliveryRepository.findById(1L));
    }

    @Test
    @Transactional
    public void testDeliveryRead2() {
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println("주문 조회" + delivery.getOrder());
        System.out.println("주문자 조회" + delivery.getOrder().getMember());
        System.out.println("주문 아이템 조회" + delivery.getOrder().getOrderItems());
    }

    @Test
    public void testDeliveryInsert2() {
        Delivery delivery = Delivery.builder().zipcode("1432").city("부산").street("123")
                .deliveryStatus(DeliveryStatus.READY).build();

        Order order = orderRepository.findById(1L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testDelete() {
        orderRepository.deleteById(null);
    }
}
