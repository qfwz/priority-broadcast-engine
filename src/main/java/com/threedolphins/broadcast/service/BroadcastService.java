package com.threedolphins.broadcast.service;

import com.threedolphins.broadcast.model.Customer;
import com.threedolphins.broadcast.model.MessageStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@ApplicationScoped
public class BroadcastService {

    @Inject
    private WhatsAppIntegrationService integrationService;

    @Inject
    private ExecutorService broadcastExecutor;

    public void startBroadcast(List<Customer> customers, Runnable onComplete) {

        CompletableFuture<?>[] futures = customers.stream()
                .map(this::sendToOne)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures)
                .whenComplete((result, error) -> onComplete.run());
    }

    private CompletableFuture<Void> sendToOne(Customer customer) {

        customer.setStatus(MessageStatus.SENDING);

        return CompletableFuture
                .supplyAsync(
                        () -> integrationService.sendMessage(customer.getPhoneNumber()),
                        broadcastExecutor
                )
                .thenAccept(status -> {
                    customer.setStatus(status);

                    if (status == MessageStatus.FAILED) {
                        customer.setLastError("Delivery failed (simulated).");
                    }
                })
                .exceptionally(error -> {
                    customer.setStatus(MessageStatus.FAILED);
                    customer.setLastError("Error: " + error.getMessage());
                    return null;
                });
    }
}