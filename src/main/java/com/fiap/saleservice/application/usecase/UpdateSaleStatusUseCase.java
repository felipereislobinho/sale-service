package com.fiap.saleservice.application.usecase;

import com.fiap.saleservice.domain.entity.Sale;
import com.fiap.saleservice.domain.entity.SaleStatus;
import com.fiap.saleservice.exception.NotFoundException;
import com.fiap.saleservice.infrastructure.client.VehicleClient;
import com.fiap.saleservice.infrastructure.gateway.SaleGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateSaleStatusUseCase {

    private final SaleGateway gateway;
    private final VehicleClient vehicleClient;


    public UpdateSaleStatusUseCase(SaleGateway gateway, VehicleClient vehicleClient) {
        this.gateway = gateway;
        this.vehicleClient = vehicleClient;
    }

    public Sale execute(Long saleId, SaleStatus newStatus) {
        Sale sale = gateway.findById(saleId)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada com o ID informado"));

        sale.setStatus(newStatus);
        if (newStatus == SaleStatus.CONCLUIDO) {
            vehicleClient.markVehicleAsSold(sale.getVehicleId());
        }
        return gateway.save(sale);
    }
}
