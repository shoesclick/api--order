package com.shoesclick.api.order.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerCircuitBreakerConfig {

    Logger LOOGER = LoggerFactory.getLogger(LoggerCircuitBreakerConfig.class);

    @Bean
    public RegistryEventConsumer<CircuitBreaker> logEvent() {
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                entryAddedEvent
                        .getAddedEntry()
                        .getEventPublisher()
                        .onStateTransition(event ->
                                LOOGER.info(event.toString())
                        );
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                entryRemoveEvent
                        .getRemovedEntry()
                        .getEventPublisher()
                        .onStateTransition(event ->
                                LOOGER.info(event.toString())
                        );
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                entryReplacedEvent
                        .getOldEntry()
                        .getEventPublisher()
                        .onStateTransition(event ->
                                LOOGER.info(event.toString())
                        );
                entryReplacedEvent
                        .getNewEntry()
                        .getEventPublisher()
                        .onStateTransition(event ->
                                LOOGER.info(event.toString())
                        );

            }
        };
    }
}
