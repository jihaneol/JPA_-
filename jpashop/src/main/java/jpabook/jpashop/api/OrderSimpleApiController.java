package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepositor;

//     무한 루프
    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        System.out.println("v1");
        List<Order> all = orderRepositor.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> orderV2() {
        // ORDER 2개
        // N + 1 문제
        List<Order> orders = orderRepositor.findAllByString(new OrderSearch());
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> orderV3() {
        List<Order> orders = orderRepositor.findAllWithMemberDelive();
        return orders.stream()
                .map(o ->new OrderSimpleQueryDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4() {
        return orderRepositor.findOrderDtos();
    }



}
