package com.threedolphins.broadcast.service;

import com.threedolphins.broadcast.model.MessageStatus;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class WhatsAppIntegrationService {

    private static final int MIN_DELAY_MS = 1000;
    private static final int MAX_DELAY_MS = 2000;
    private static final int FAILURE_RATE_PERCENT = 10;

    public MessageStatus sendMessage(String phoneNumber) {
        try {
            int delayMs = ThreadLocalRandom.current().nextInt(MIN_DELAY_MS, MAX_DELAY_MS + 1);
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return MessageStatus.FAILED;
        }

        int roll = ThreadLocalRandom.current().nextInt(100);
        if (roll < FAILURE_RATE_PERCENT) {
            return MessageStatus.FAILED;
        }
        return MessageStatus.SENT;
    }
}