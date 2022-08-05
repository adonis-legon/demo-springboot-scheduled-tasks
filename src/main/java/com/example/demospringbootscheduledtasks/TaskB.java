package com.example.demospringbootscheduledtasks;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskB {
    private Random random;

    public TaskB() {
        random = new Random();
    }

    @Async("threadPoolTaskBExecutor")
    public CompletableFuture<Void> run(){
        try {
            System.out.println("TaskB with id: " + Thread.currentThread().getId() + " started...");
            Thread.sleep(this.random.nextInt(10) * 1000);
            System.out.println("TaskB with id: " + Thread.currentThread().getId() + " finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
}
