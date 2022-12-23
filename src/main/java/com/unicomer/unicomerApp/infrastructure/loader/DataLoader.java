package com.unicomer.unicomerApp.infrastructure.loader;

import com.unicomer.model.Pharmacy;
import com.unicomer.unicomerApp.domain.data.PharmaciesSingleton;
import com.unicomer.unicomerApp.infrastructure.client.PharmacyControllerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Clase encargada de hacer la carga previa de datos
 **/
@Component
@Slf4j
public class DataLoader implements ApplicationRunner {
    @Value("${pharmacies.url}")
    String endpoint;
    @Autowired
    PharmacyControllerClient client;
    @Override
    public void run(ApplicationArguments args) {
        log.info("[DataLoader.run] Se inicia carga de datos");
        log.info("[DataLoader.run] Llamando a endpoint -> " + endpoint);
        List<com.unicomer.unicomerApp.infrastructure.client.model.Pharmacy> pharmacies = client.getAll();
        HashMap<String, List<Pharmacy>> hash = new HashMap<>();
        log.info("[DataLoader.run] llamado exitoso, se procede a instanciar singleton");
        pharmacies.stream().forEach(p -> {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setName(p.getLocal_nombre());
            pharmacy.setAdress(p.getLocal_direccion());
            pharmacy.setPhone(p.getLocal_telefono());
            pharmacy.setLatitude(p.getLocal_lat());
            pharmacy.setLongitude(p.getLocal_lng());
            if(!hash.containsKey(p.getComuna_nombre().toUpperCase()))
                hash.put(p.getComuna_nombre().toUpperCase(), new ArrayList<>());
            hash.get(p.getComuna_nombre().toUpperCase()).add(pharmacy);
        });

        PharmaciesSingleton.getInstance(hash);
        log.info("[DataLoader.run] Singleton instanciado con exito");
    }
}
