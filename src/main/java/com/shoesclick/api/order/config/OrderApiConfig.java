package com.shoesclick.api.order.config;

import com.shoesclick.api.order.config.properties.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class OrderApiConfig {

}
