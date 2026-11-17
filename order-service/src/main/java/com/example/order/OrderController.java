package com.example.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class OrderController {

    private final RestClient restClient;

    public OrderController(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @GetMapping("/order")
    public String createOrder() {
        String paymentResult = restClient.get()
            .uri("http://PAYMENT-SERVICE/payment")
            .headers(headers -> headers.setBasicAuth("student", "java123"))
            .retrieve()
            .body(String.class);

        return "Order created. Payment Service replied: " + paymentResult;
    }
}
