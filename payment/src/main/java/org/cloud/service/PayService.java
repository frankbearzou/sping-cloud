package org.cloud.service;

import java.util.List;

import org.cloud.entity.Pay;

public interface PayService {
    Pay addPayment(Pay pay);
    void deletePayment(int id);
    Pay updatePayment(Pay pay);
    Pay getPayment(int id);
    List<Pay> getPaymentList();
}
