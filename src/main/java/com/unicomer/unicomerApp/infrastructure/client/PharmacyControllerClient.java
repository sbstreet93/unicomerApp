package com.unicomer.unicomerApp.infrastructure.client;

import com.unicomer.unicomerApp.infrastructure.client.model.Pharmacy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.unicomer.unicomerApp.infrastructure.config.PharmacyControllerClientConfig;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        value = "pharmacyController",
        url = "${pharmacies.url}",
        configuration = PharmacyControllerClientConfig.class
)
public interface PharmacyControllerClient {
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    List<Pharmacy> getAll();
}