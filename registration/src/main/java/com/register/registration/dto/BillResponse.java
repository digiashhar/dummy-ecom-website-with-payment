package com.register.registration.dto;

import lombok.Data;

@Data
public class BillResponse {
    private Long billId;
    private Long userId;
    private double totalPrice;

    public BillResponse(Long billId, Long userId, double totalPrice) {
        this.billId = billId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
