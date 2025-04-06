package com.fiap.saleservice.infrastructure.controller.dto;

public class SaleRequest {
    private Long vehicleId;
    private String buyer;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
