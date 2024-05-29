package org.cloud.controller;

import java.util.List;

import jakarta.annotation.Resource;
import org.cloud.commons.dto.PayDto;
import org.cloud.commons.feign.PayFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {
    private PayFeignApi payFeignApi;

    public OrderController(PayFeignApi payFeignApi) {
        this.payFeignApi = payFeignApi;
    }

    @GetMapping("/feign/pay/{id}")
    public PayDto getPay(@PathVariable("id") int id) {
        return payFeignApi.getPay(id);
    }

    @GetMapping("/feign/pay")
    public List<PayDto> getPayList() {
        return payFeignApi.getPayList();
    }
}
