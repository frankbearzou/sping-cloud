package org.cloud.controller;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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

    @GetMapping("/feign/pay/bulkhead-thread-pool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "bulkheadThreadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> payBulkheadThreadPool(@PathVariable("id") Integer id) {
        Random r = new Random();
        int index = r.nextInt();
        logger.info("enter " + index);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> payFeignApi.getPayCircuit(id));
        logger.info("leave " + index);
        return future;
    }

    public CompletableFuture<String> bulkheadThreadPoolFallback(Integer id, Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> "bulkhead thread pool fallback: system busy, please try again later. " + throwable.getMessage());
    }

    @GetMapping("/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "rateLimitFallback")
    public String payRatelimit(@PathVariable("id") Integer id) {
        return payFeignApi.getPayCircuit(id);
    }

    public String rateLimitFallback(Integer id, Throwable throwable) {
        return "rate limit " + id + " fallback: " + throwable.getMessage();
    }
}
