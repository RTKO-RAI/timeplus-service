package com.rbinternational.neura.timeplus.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(5000, TimeUnit.MILLISECONDS, 15, TimeUnit.MINUTES, true);
    }
}
