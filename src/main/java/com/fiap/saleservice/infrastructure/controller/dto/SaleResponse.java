package com.fiap.saleservice.infrastructure.controller.dto;

import com.fiap.saleservice.domain.entity.SaleStatus;

import java.time.LocalDateTime;

public class SaleResponse {
    private Long id;
    private Long vehicleId;
    private Double saleValue;
    private String buyer;
    private LocalDateTime saleDate;
    private SaleStatus status;
    private Long paymentId;

    public SaleResponse(Long id, Long vehicleId, Double saleValue, String buyer, LocalDateTime saleDate, SaleStatus status, Long paymentId) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.saleValue = saleValue;
        this.buyer = buyer;
        this.saleDate = saleDate;
        this.status = status;
        this.paymentId = paymentId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Double saleValue) {
        this.saleValue = saleValue;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
}
