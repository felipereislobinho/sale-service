package com.fiap.saleservice.application.usecase;

import com.fiap.saleservice.domain.entity.Sale;
import com.fiap.saleservice.infrastructure.gateway.SaleGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSalesUseCase {

    private final SaleGateway gateway;

    public ListSalesUseCase(SaleGateway gateway) {
        this.gateway = gateway;
    }

    public List<Sale> execute() {
        return gateway.findAll();
    }
}
