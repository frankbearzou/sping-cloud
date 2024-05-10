package org.cloud.service.impl;

import java.util.List;

import org.cloud.entity.Pay;
import org.cloud.repository.PayRepository;
import org.cloud.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayserviceImpl implements PayService {
    private PayRepository payRepository;

    public PayserviceImpl(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @Override
    public Pay addPayment(Pay pay) {
        return payRepository.saveAndFlush(pay);
    }

    @Override
    public void deletePayment(int id) {
        payRepository.deleteById(id);
    }

    @Override
    public Pay updatePayment(Pay pay) {
        return payRepository.saveAndFlush(pay);
    }

    @Override
    public Pay getPayment(int id) {
        return payRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pay> getPaymentList() {
        return payRepository.findAll();
    }
}
