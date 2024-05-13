package org.cloud.controller;

import java.util.List;

import org.cloud.dto.PayDTO;
import org.cloud.entity.Pay;
import org.cloud.service.PayService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {
    private PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/{id}")
    public Pay getPay(@PathVariable("id") int id) {
        return payService.getPayment(id);
    }

    @GetMapping
    public List<Pay> getPayList() {
        return payService.getPaymentList();
    }

    @PostMapping
    public Pay add(@RequestBody Pay pay) {
        return payService.addPayment(pay);
    }

    @PutMapping
    public Pay update(@RequestBody PayDTO payDTO) {
        Pay pay = payService.getPayment(payDTO.getId());
        if (pay != null) {
            payDTO.updatePay(pay);
        }
        return payService.updatePayment(pay);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        payService.deletePayment(id);
    }
}
