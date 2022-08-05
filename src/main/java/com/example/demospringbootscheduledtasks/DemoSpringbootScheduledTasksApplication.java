package com.example.demospringbootscheduledtasks;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class DemoSpringbootScheduledTasksApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootScheduledTasksApplication.class, args);
	}

	@Bean(name = "threadPoolTaskAExecutor")
	public Executor taskAExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Integer.valueOf(5));
		executor.setMaxPoolSize(Integer.valueOf(10));
		executor.setThreadNamePrefix("TaskA-");
		executor.initialize();
		return executor;
	}
	
	@Bean(name = "threadPoolTaskBExecutor")
	public Executor taskBExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Integer.valueOf(5));
		executor.setMaxPoolSize(Integer.valueOf(10));
		executor.setThreadNamePrefix("TaskB-");
		executor.initialize();
		return executor;
	}
}
