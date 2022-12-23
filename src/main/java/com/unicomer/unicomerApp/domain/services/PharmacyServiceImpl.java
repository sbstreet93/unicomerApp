package com.unicomer.unicomerApp.domain.services;

import com.unicomer.model.Pharmacy;
import com.unicomer.unicomerApp.domain.data.PharmaciesSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class PharmacyServiceImpl implements PharmacyService{
    @Override
    public List<Pharmacy> getPharmaciesByCity(String city) {
        PharmaciesSingleton singleton = PharmaciesSingleton.getInstance(new HashMap<>());
        if(singleton.getHash().containsKey(city.toUpperCase()))
            return singleton.getHash().get(city.toUpperCase());
        return new ArrayList<>();
    }
}
