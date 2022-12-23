package com.unicomer.unicomerApp.infrastructure.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PharmacyControllerClientConfig extends BaseConfig {

    @Bean
    public Decoder feignDecoder() {
        return (response, type) -> {
            String jsonStr = Proxy.getJsonStr(response);
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            return new ObjectMapper().readValue(jsonStr, javaType);
        };
    }

}
