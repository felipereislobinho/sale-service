package com.fiap.saleservice.infrastructure.controller.dto;

import com.fiap.saleservice.domain.entity.Sale;

public class CreateSaleResponse {
    private final Sale sale;
    private final Long paymentId;

    public CreateSaleResponse(Sale sale, Long paymentId) {
        this.sale = sale;
        this.paymentId = paymentId;
    }

    public Sale getSale() {
        return sale;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
