package com.fiap.saleservice.infrastructure.controller;

import com.fiap.saleservice.application.usecase.CreateSaleUseCase;
import com.fiap.saleservice.application.usecase.ListSalesUseCase;
import com.fiap.saleservice.application.usecase.UpdateSaleStatusUseCase;
import com.fiap.saleservice.domain.entity.Sale;
import com.fiap.saleservice.domain.entity.SaleStatus;
import com.fiap.saleservice.infrastructure.client.dto.PaymentRequest;
import com.fiap.saleservice.infrastructure.client.dto.PaymentResponse;
import com.fiap.saleservice.infrastructure.controller.dto.CreateSaleResponse;
import com.fiap.saleservice.infrastructure.controller.dto.SaleRequest;
import com.fiap.saleservice.infrastructure.controller.dto.SaleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final CreateSaleUseCase createSaleUseCase;
    private final ListSalesUseCase listSalesUseCase;
    private final UpdateSaleStatusUseCase updateSaleStatusUseCase;

    public SaleController(CreateSaleUseCase createSaleUseCase, ListSalesUseCase listSalesUseCase, UpdateSaleStatusUseCase updateSaleStatusUseCase) {
        this.createSaleUseCase = createSaleUseCase;
        this.listSalesUseCase = listSalesUseCase;
        this.updateSaleStatusUseCase = updateSaleStatusUseCase;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest request) {

        CreateSaleResponse response  = createSaleUseCase.execute(request.getVehicleId(), request.getBuyer());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SaleResponse(
                        response.getSale().getId(),
                        response.getSale().getVehicleId(),
                        response.getSale().getSaleValue(),
                        response.getSale().getBuyer(),
                        response.getSale().getSaleDate(),
                        response.getSale().getStatus(),
                        response.getPaymentId() // Aqui está o Payment ID!
                )
        );

    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> list() {
        List<SaleResponse> sales = listSalesUseCase.execute()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sales);
    }
    @PutMapping("/{id}/concluir")
    public ResponseEntity<SaleResponse> concluirVenda(@PathVariable Long id) {
        Sale sale = updateSaleStatusUseCase.execute(id, SaleStatus.CONCLUIDO);
        return ResponseEntity.ok(toResponse(sale));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<SaleResponse> cancelarVenda(@PathVariable Long id) {
        Sale sale = updateSaleStatusUseCase.execute(id, SaleStatus.CANCELADO);
        return ResponseEntity.ok(toResponse(sale));
    }

    private SaleResponse toResponse(Sale sale) {
        return new SaleResponse(
                sale.getId(),
                sale.getVehicleId(),
                sale.getSaleValue(),
                sale.getBuyer(),
                sale.getSaleDate(),
                sale.getStatus(),
                null // No list, ainda não vamos trazer o paymentId (opcional)
        );
    }

    private SaleResponse toResponse(Sale sale, Long paymentId) {
        return new SaleResponse(
                sale.getId(),
                sale.getVehicleId(),
                sale.getSaleValue(),
                sale.getBuyer(),
                sale.getSaleDate(),
                sale.getStatus(),
                paymentId
        );
    }

}
