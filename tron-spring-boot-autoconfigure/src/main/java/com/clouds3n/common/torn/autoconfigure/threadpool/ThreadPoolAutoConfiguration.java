package com.clouds3n.common.torn.autoconfigure.threadpool;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.system.StarterAutoConfiguration;
import com.clouds3n.common.torn.autoconfigure.threadpool.properties.CommonTaskExecutorProperties;
import com.clouds3n.common.torn.autoconfigure.threadpool.properties.CommonTaskSchedulerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author CloudS3n
 * @date 2020-06-25 16:12
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({CommonTaskExecutorProperties.class, CommonTaskSchedulerProperties.class})
@ConditionalOnProperty(name = "config.starter.enable-thread-pool", havingValue = "true")
@AutoConfigureAfter(StarterAutoConfiguration.class)
public class ThreadPoolAutoConfiguration {

    public ThreadPoolAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_THREAD_POOL_AUTO_CONFIGURE);
    }

    @Bean
    @ConditionalOnProperty(name = "config.starter.enable-thread-pool", havingValue = "true")
    public ThreadPoolTaskExecutor commonTaskExecutor(CommonTaskExecutorProperties properties) {
        log.info(AutoConfigConstants.LOADING_THREAD_POOL_TASK_EXECUTOR);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setAllowCoreThreadTimeOut(properties.getAllowCoreThreadTimeout());
        executor.setKeepAliveSeconds((int) properties.getKeepAliveSeconds().getSeconds());
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnProperty(name = "config.starter.enable-thread-pool", havingValue = "true")
    public ThreadPoolTaskScheduler commonTaskScheduler(CommonTaskSchedulerProperties properties) {
        log.info(AutoConfigConstants.LOADING_THREAD_POOL_TASK_SCHEDULER);
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(properties.getPoolSize());
        scheduler.setThreadNamePrefix(properties.getThreadNamePrefix());
        scheduler.initialize();
        return scheduler;
    }
}
