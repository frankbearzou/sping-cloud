package org.cloud.dto;

import java.math.BigDecimal;

import org.cloud.entity.Pay;

public class PayDTO {
    private int id;
    private String payNo;
    private String orderNo;
    private int userId;
    private BigDecimal amount;

    public PayDTO() {
    }

    public void updatePay(Pay pay) {
        pay.setPayNo(payNo);
        pay.setOrderNo(orderNo);
        pay.setUserId(userId);
        pay.setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
