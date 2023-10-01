package com.redeemerlives.orderservice.config;

//import com.redeemerlives.orderservice.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
//@RequiredArgsConstructor
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

//    private final LoadBalancedExchangeFilterFunction filterFunction;
//
//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl("http://inventory_service")
//                .filter(filterFunction)
//                .build();
//    }

//    @Bean
//    public InventoryClient inventoryClient() {
//        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
//                .builder(WebClientAdapter.forClient(webClient()))
//                .build();
//        return httpServiceProxyFactory.createClient(InventoryClient.class);
//    }

}
