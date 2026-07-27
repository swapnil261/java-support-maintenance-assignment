package com.assignment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 {

    // FIX: Use AtomicInteger because processedCount++ is not thread-safe and
    // can lose updates when multiple threads increment the counter concurrently.
    private AtomicInteger processedCount = new AtomicInteger(0);

    public void process(List<StatementRecord> records) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StatementRecord record : records) {
            executor.submit(() -> {
                processRecord(record);

                // FIX: incrementAndGet() performs an atomic increment,
                // ensuring the processed count remains accurate.
                processedCount.incrementAndGet();
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
    }

    public int getProcessedCount() {
        return processedCount.get();
    }

    // Assume this method already exists in the original codebase.
    private void processRecord(StatementRecord record) {
        // Existing implementation
    }
}
