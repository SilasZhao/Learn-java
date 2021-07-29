package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
/**
 * 定义
 */

public class ExecutorConfig {
    @Bean("exportServiceExecutor")
    public Executor exportServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数量：当前机器的核心数
        executor.setCorePoolSize(
                Runtime.getRuntime().availableProcessors()
        );
        //最大线程数
        executor.setMaxPoolSize(
                Runtime.getRuntime().availableProcessors() *2
        );
        //队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //线程池中的线程名前缀
        executor.setThreadNamePrefix("export-");
        //拒绝策略：直接拒绝
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.initialize();
        return executor;
    }
}
