package com.unicomer.unicomerApp.infrastructure.config;

import com.squareup.okhttp.OkHttpClient;
import com.unicomer.unicomerApp.infrastructure.config.decoder.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;

abstract class BaseConfig {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public FeignErrorDecoder decoder() {
        return new FeignErrorDecoder();
    }
}
