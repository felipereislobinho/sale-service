package com.fiap.saleservice.infrastructure.client.dto;

public class PaymentResponse {

    private Long id;
    private Long saleId;
    private double saleValue;
    private String status;

    public PaymentResponse() {
    }

    public PaymentResponse(Long id, Long saleId, double saleValue, String status) {
        this.id = id;
        this.saleId = saleId;
        this.saleValue = saleValue;
        this.status = status;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public double getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(double saleValue) {
        this.saleValue = saleValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
