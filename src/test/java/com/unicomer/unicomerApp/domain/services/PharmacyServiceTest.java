package com.unicomer.unicomerApp.domain.services;

import com.unicomer.model.Pharmacy;
import com.unicomer.unicomerApp.domain.business.PharmacyBusiness;
import com.unicomer.unicomerApp.domain.data.PharmaciesSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class PharmacyServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    PharmacyService service;
    @InjectMocks
    private PharmacyBusiness pharmacyService;

    private static final String CITY = "valparaiso";

    @Before
    public void init()
    {
        Mockito.
                when(service.getPharmaciesByCity(CITY))
                .thenReturn(getPharmacies());
        mockMvc = standaloneSetup(pharmacyService).build();
    }

    @Test
    public void shouldReturnResponseSuccess(){
        List<Pharmacy> list = pharmacyService.getPharmaciesByCity(CITY).getBody();
        Assert.assertTrue(list.size() == 1);

    }

    private List<Pharmacy> getPharmacies(){
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setLongitude("-1111111");
        pharmacy.setLatitude("-222222");
        pharmacy.setPhone("123456");
        pharmacy.setAdress("Calle 1 A");
        pharmacy.setName("Farmacia ejemplo");
        List<Pharmacy> list = new ArrayList<>();
        list.add(pharmacy);
        return list;
    }
}
