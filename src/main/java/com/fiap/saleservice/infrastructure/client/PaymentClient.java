package com.fiap.saleservice.infrastructure.client;

import com.fiap.saleservice.infrastructure.client.dto.PaymentRequest;
import com.fiap.saleservice.infrastructure.client.dto.PaymentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service", url = "${client.payment.url}")
public interface PaymentClient {

    @PostMapping(value = "/payments/payments", consumes = "application/json", produces = "application/json")
    PaymentResponse createPayment(PaymentRequest paymentRequest);
}
