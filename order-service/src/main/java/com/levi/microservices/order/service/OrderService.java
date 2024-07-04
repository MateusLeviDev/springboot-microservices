package com.levi.microservices.order.service;

import com.levi.microservices.order.client.InventoryClient;
import com.levi.microservices.order.domain.Order;
import com.levi.microservices.order.dto.OrderRequest;
import com.levi.microservices.order.event.OrderPlacedEvent;
import com.levi.microservices.order.exception.OrderCreationException;
import com.levi.microservices.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        var isInStock = this.inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .price(orderRequest.price())
                    .quantity(orderRequest.quantity())
                    .skuCode(orderRequest.skuCode())
                    .build();
            orderRepository.save(order);
            log.info("Successfully order");

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),
                    orderRequest.userDetails().email());
            log.info("START - Sending Order {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("FINISH");
        } else {
            log.error("issue with creating the order ");
            throw new OrderCreationException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
