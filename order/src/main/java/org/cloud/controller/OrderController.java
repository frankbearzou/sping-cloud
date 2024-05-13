package org.cloud.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.cloud.dto.PayDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@Tag(name = "order controller")
public class OrderController {
    private static final String URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/pay/{id}")
    public PayDTO getPay(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(URL + "/pay/" + id, PayDTO.class);
    }

    @PostMapping("/pay")
    public PayDTO addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(URL + "/pay", payDTO, PayDTO.class);
    }
}
