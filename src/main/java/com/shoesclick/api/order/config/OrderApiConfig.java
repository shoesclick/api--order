package com.shoesclick.api.order.config;

import com.shoesclick.api.order.config.properties.MqProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MqProperties.class)
public class OrderApiConfig {
}
