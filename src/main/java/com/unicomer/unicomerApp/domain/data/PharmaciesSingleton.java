package com.unicomer.unicomerApp.domain.data;

import com.unicomer.model.Pharmacy;

import java.util.HashMap;
import java.util.List;

public class PharmaciesSingleton {
    private static PharmaciesSingleton instance;
    public HashMap<String, List<Pharmacy>> hash;

    private PharmaciesSingleton(HashMap<String, List<Pharmacy>> hash) {
        this.hash = hash;
    }

    public static PharmaciesSingleton getInstance(HashMap<String, List<Pharmacy>> hash) {
        if (instance == null) {
            instance = new PharmaciesSingleton(hash);
        }
        return instance;
    }

    public HashMap<String, List<Pharmacy>> getHash(){
        return hash;
    }
}