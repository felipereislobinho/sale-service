package com.fiap.saleservice.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;
    private Double saleValue;
    private String buyer;
    private LocalDateTime saleDate;

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    public Sale() {}

    public Sale(Long vehicleId, Double saleValue, String buyer, LocalDateTime saleDate) {
        this.vehicleId = vehicleId;
        this.saleValue = saleValue;
        this.buyer = buyer;
        this.saleDate = saleDate;
        this.status = SaleStatus.AGUARDANDO_PAGAMENTO;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public String getBuyer() {
        return buyer;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setSaleValue(Double saleValue) {
        this.saleValue = saleValue;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
}
