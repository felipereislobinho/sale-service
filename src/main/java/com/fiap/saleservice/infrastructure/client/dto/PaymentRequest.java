package com.fiap.saleservice.infrastructure.client.dto;

public class PaymentRequest {
    private Long saleId;
    private Double value;

    public PaymentRequest() {}

    public PaymentRequest(Long saleId, Double value) {
        this.saleId = saleId;
        this.value = value;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
