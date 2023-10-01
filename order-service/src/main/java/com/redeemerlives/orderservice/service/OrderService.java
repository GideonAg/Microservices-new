package com.redeemerlives.orderservice.service;

//import com.redeemerlives.orderservice.client.InventoryClient;
import com.redeemerlives.orderservice.dto.InventoryResponse;
import com.redeemerlives.orderservice.dto.OrderLineItemsDto;
import com.redeemerlives.orderservice.dto.OrderRequest;
import com.redeemerlives.orderservice.event.OrderPlacedEvent;
import com.redeemerlives.orderservice.model.Order;
import com.redeemerlives.orderservice.model.OrderLineItems;
import com.redeemerlives.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
//    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::orderLineItems).toList();

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("lb://inventory-service/api/v1/inventory/",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

//        String variable = orderRequest.getOrderLineItemsDtoList().stream().map(OrderLineItemsDto::getSkuCode).toString();
//        InventoryResponse[] inventoryResponses = inventoryClient.inventoryResponses(variable.toString());

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
//        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("OrderPlacedNotification", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed Successfully";
        }
        else {
            throw new IllegalStateException("Product is out of stock, please try again later");
        }
    }

    private OrderLineItems orderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
