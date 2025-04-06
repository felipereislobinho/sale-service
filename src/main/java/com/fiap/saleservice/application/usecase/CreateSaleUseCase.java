package com.fiap.saleservice.application.usecase;

import com.fiap.saleservice.domain.entity.Sale;
import com.fiap.saleservice.domain.entity.SaleStatus;
import com.fiap.saleservice.exception.BusinessException;
import com.fiap.saleservice.infrastructure.client.PaymentClient;
import com.fiap.saleservice.infrastructure.client.VehicleClient;
import com.fiap.saleservice.infrastructure.client.dto.PaymentRequest;
import com.fiap.saleservice.infrastructure.client.dto.PaymentResponse;
import com.fiap.saleservice.infrastructure.client.response.VehicleResponse;
import com.fiap.saleservice.infrastructure.controller.dto.CreateSaleResponse;
import com.fiap.saleservice.infrastructure.gateway.SaleGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateSaleUseCase {

    private final SaleGateway gateway;
    private final VehicleClient vehicleClient;
    private final PaymentClient paymentClient;


    public CreateSaleUseCase(SaleGateway gateway, VehicleClient vehicleClient, PaymentClient paymentClient) {
        this.gateway = gateway;
        this.vehicleClient = vehicleClient;
        this.paymentClient = paymentClient;
    }

    public CreateSaleResponse execute(Long vehicleId, String buyer) {


        VehicleResponse vehicle = vehicleClient.getVehicleById(vehicleId);


        if (!"DISPONIVEL".equalsIgnoreCase(vehicle.getStatus())) {
            throw new BusinessException("Veículo não está disponível para venda");
        }

        Sale sale = new Sale(vehicleId, vehicle.getPreco(), buyer, LocalDateTime.now());
        sale.setStatus(SaleStatus.PENDENTE);

        // Salva primeiro para gerar o ID
        Sale savedSale = gateway.save(sale);

        try {
            // Depois de salvo e com ID, chama o serviço de pagamento
            PaymentResponse paymentResponse = paymentClient.createPayment(new PaymentRequest(savedSale.getId(), savedSale.getSaleValue()));
            return new CreateSaleResponse(savedSale, paymentResponse.getId());
        } catch (Exception ex) {
            // Se der erro no pagamento, remove a venda criada.
            gateway.delete(savedSale.getId());
            throw new BusinessException("Erro ao criar pagamento. Venda cancelada.", ex);
        }


//        // Depois de salvo e com ID, chama o serviço de pagamento
//        PaymentResponse paymentResponse = paymentClient.createPayment(new PaymentRequest(sale.getId(), sale.getSaleValue()));
//
//        return new CreateSaleResponse(savedSale, paymentResponse.getId());


    }
}
