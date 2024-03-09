package com.shoesclick.api.order.config;

import com.shoesclick.api.order.handler.OpenFeingHandler;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class OpenFeingConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new OpenFeingHandler();
    }

}
