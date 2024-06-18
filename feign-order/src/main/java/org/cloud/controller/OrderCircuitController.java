package org.cloud.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.cloud.commons.feign.PayFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "fallback")
    public String payCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.getPayCircuit(id);
    }

    public String fallback(Integer id, Throwable throwable) {
        return "fallback: system busy, please try again later. " + throwable.getMessage();
    }
}
