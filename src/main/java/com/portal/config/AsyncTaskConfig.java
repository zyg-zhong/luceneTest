package com.portal.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置类.
 * @author allen-zyg.
 * @version 1.0.0.
 */
@Configuration
public class AsyncTaskConfig implements AsyncConfigurer {
    /**
     * springboot集成多线程并发编程：线程池配置.
     * @return Executor.
     */
    @Override
    @Bean //交给容器处理
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5); // 最小线程数
        taskExecutor.setMaxPoolSize(10); // 最大线程数
        taskExecutor.setQueueCapacity(25); // 等待队列
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 可能发生异常的处理.
     * @return null.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
