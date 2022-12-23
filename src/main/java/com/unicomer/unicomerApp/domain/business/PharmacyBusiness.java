package com.unicomer.unicomerApp.domain.business;

import com.unicomer.model.Pharmacy;
import com.unicomer.unicomerApp.PharmaciesApiDelegate;
import com.unicomer.unicomerApp.domain.services.PharmacyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class PharmacyBusiness implements PharmaciesApiDelegate {

    @Autowired
    PharmacyService service;
    @Override
    public ResponseEntity<List<Pharmacy>> getPharmaciesByCity(String city) {
        List<Pharmacy> pharmacies = service.getPharmaciesByCity(city);
        if(!pharmacies.isEmpty())
            return new ResponseEntity<>(pharmacies, HttpStatus.OK);
        log.info("[PharmacyService.getPharmaciesByCity] no se han encontrado resultados para la busqueda dada");
        log.info("[PharmacyService.getPharmaciesByCity] (PARAM) -> " + city.toUpperCase());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not found data with the city given");
    }
}
