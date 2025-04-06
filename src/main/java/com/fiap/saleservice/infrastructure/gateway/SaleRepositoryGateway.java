package com.fiap.saleservice.infrastructure.gateway;

import com.fiap.saleservice.domain.entity.Sale;
import com.fiap.saleservice.infrastructure.repository.SaleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SaleRepositoryGateway implements SaleGateway {

    private final SaleRepository repository;

    public SaleRepositoryGateway(SaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sale save(Sale sale) {
        return repository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return repository.findById(id);
    }
}
