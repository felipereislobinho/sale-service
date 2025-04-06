package com.fiap.saleservice.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fiap.saleservice.infrastructure.client.response.VehicleResponse;
import com.fiap.saleservice.exception.NotFoundException;

@Component
public class VehicleClient {

    private final RestTemplate restTemplate;
    private final String vehicleServiceUrl;


    public VehicleClient(@Value("${vehicle-service.url}") String vehicleServiceUrl) {
        this.vehicleServiceUrl = vehicleServiceUrl;
        this.restTemplate = new RestTemplate();
    }

    public VehicleResponse getVehicleById(Long vehicleId) {
        try {
            String url = vehicleServiceUrl + "/vehicles/" + vehicleId;
            return restTemplate.getForObject(url, VehicleResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Veículo não encontrado no serviço de veículos");
        }
    }

    public void markVehicleAsSold(Long vehicleId) {
        String url = vehicleServiceUrl + "/vehicles/" + vehicleId + "/sell";
//        restTemplate.patchForObject(url, null, Void.class);
        restTemplate.put(url, null);
    }
}
