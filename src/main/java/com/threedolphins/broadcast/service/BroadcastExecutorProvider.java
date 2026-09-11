package com.threedolphins.broadcast.service;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class BroadcastExecutorProvider {

    private static final int POOL_SIZE = 10;

    private final ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

    @Produces
    @ApplicationScoped
    public ExecutorService broadcastExecutor() {
        return executorService;
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }
}