package com.levi.microservices.order.service;

import com.levi.microservices.order.domain.Order;
import com.levi.microservices.order.dto.OrderRequest;
import com.levi.microservices.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        orderRepository.save(
                Order.builder()
                        .orderNumber(UUID.randomUUID().toString())
                        .price(orderRequest.price())
                        .quantity(orderRequest.quantity())
                        .skuCode(orderRequest.skuCode())
                        .build()
        );
        log.info("Successfully order");
    }
}
