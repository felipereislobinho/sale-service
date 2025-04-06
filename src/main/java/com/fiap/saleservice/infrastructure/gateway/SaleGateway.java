package com.fiap.saleservice.infrastructure.gateway;

import com.fiap.saleservice.domain.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleGateway {
    Sale save(Sale sale);
    List<Sale> findAll();
    Optional<Sale> findById(Long id);
}
