package com.redeemerlives.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "OrderPlacedNotification")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        // send out email notification
        log.info("received notification for order: {}", orderPlacedEvent.getOrderNumber());
    }
}
