package com.unicomer.unicomerApp.domain.services;

import com.unicomer.model.Pharmacy;

import java.util.List;

public interface PharmacyService {
    List<Pharmacy> getPharmaciesByCity(String city);
}
