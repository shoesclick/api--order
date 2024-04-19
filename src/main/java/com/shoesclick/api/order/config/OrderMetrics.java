package com.shoesclick.api.order.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class OrderMetrics {

    private final Counter orderProcess;

    private final Counter orderErrors;

    public OrderMetrics(MeterRegistry registry) {
        this.orderProcess = Counter.builder("order_process_total")
                .description("Total de Pedidos Processados")
                .register(registry);

        this.orderErrors = Counter.builder("order_process_errors_total")
                .description("Total de Pedidos Com erros")
                .register(registry);

    }

    public void incrementOrderSuccessCount() {
        orderProcess.increment();
    }

    public void incrementOrderErrorCount() {
        orderErrors.increment();
    }
}
