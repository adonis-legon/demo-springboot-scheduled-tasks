package com.example.demospringbootscheduledtasks;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskA {
    private Random random;

    public TaskA() {
        random = new Random();
    }

    @Async("threadPoolTaskAExecutor")
    public CompletableFuture<Void> run(){
        try {
            System.out.println("TaskA with id: " + Thread.currentThread().getId() + " started...");
            Thread.sleep(this.random.nextInt(10) * 1000);
            System.out.println("TaskA with id: " + Thread.currentThread().getId() + " finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
}
