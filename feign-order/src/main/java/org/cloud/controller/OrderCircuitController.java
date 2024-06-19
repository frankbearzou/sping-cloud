package org.cloud.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.cloud.commons.feign.PayFeignApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    private static final Logger logger = LoggerFactory.getLogger(OrderCircuitController.class);

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "fallback")
    public String payCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.getPayCircuit(id);
    }

    public String fallback(Integer id, Throwable throwable) {
        return "circuit breaker fallback: system busy, please try again later. " + throwable.getMessage();
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String payBulkhead(@PathVariable("id") Integer id) {
        logger.info("enter");
        return payFeignApi.getPayCircuit(id);
    }

    public String bulkheadFallback(Integer id, Throwable throwable) {
        return "bulkhead fallback: system busy, please try again later. " + throwable.getMessage();
    }
}
