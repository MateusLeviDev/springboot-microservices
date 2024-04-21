package com.levi.microservices.order.service;

import com.levi.microservices.order.client.InventoryClient;
import com.levi.microservices.order.domain.Order;
import com.levi.microservices.order.dto.OrderRequest;
import com.levi.microservices.order.exception.OrderCreationException;
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
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {

        var isInStock = this.inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            orderRepository.save(
                    Order.builder()
                            .orderNumber(UUID.randomUUID().toString())
                            .price(orderRequest.price())
                            .quantity(orderRequest.quantity())
                            .skuCode(orderRequest.skuCode())
                            .build()
            );
            log.info("Successfully order");
        } else {
            log.error("issue with creating the order ");
            throw new OrderCreationException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
