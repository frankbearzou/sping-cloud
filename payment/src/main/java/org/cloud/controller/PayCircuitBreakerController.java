package org.cloud.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayCircuitBreakerController {
    @GetMapping("/pay/circuit/{id}")
    public String payCircuit(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("circuit id -4");
        }
        if (id >= 1000) {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        UUID uuid = UUID.randomUUID();
        return "Hello " + uuid;
    }
}
