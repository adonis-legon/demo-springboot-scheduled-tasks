package com.example.demospringbootscheduledtasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerA {
    private final int MAX_TASK_RUNNING = 10;
    private final int AMOUNT_TASKS_TO_RUN = 50;

    @Autowired
    private TaskA taskA;

    @Scheduled(fixedDelay = 30*1000)
    public void run() {
        System.out.println("SchedulerA started...");

        List<CompletableFuture<Void>> taskresults = new ArrayList<>();

        // do some work
        try {
            int currentTaskCount = 0;
            while (currentTaskCount < AMOUNT_TASKS_TO_RUN) {
                System.out.println("SchedulerA processing tasks from: " + currentTaskCount + " to "
                        + ((currentTaskCount / MAX_TASK_RUNNING) + 1) * MAX_TASK_RUNNING + ", from a total of: "
                        + AMOUNT_TASKS_TO_RUN + "...");
                for (int i = 0; i < MAX_TASK_RUNNING; i++) {
                    currentTaskCount++;
                    taskresults.add(taskA.run());
                }

                CompletableFuture.allOf(taskresults.toArray(new CompletableFuture[taskresults.size()])).get();
                taskresults.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("SchedulerA finished!");
    }
}
