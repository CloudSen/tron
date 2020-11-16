package com.clouds3n.common.torn.autoconfigure.threadpool.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 *
 * @author CloudS3n
 * @date 2020-06-25 15:26
 */
@Validated
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "config.thread-pool.executor", ignoreUnknownFields = false)
public class CommonTaskExecutorProperties {

    @NotBlank(message = Constants.THREAD_NAME_PREFIX_INVALID)
    private String threadNamePrefix = "common-executor-";

    @Range(min = 10, max = Integer.MAX_VALUE, message = Constants.CORE_POLL_SIZE_INVALID)
    private Integer corePoolSize = 15;

    @Range(min = 50, max = Integer.MAX_VALUE, message = Constants.MAX_POLL_SIZE_INVALID)
    private Integer maxPoolSize = 50;

    @Range(min = 0, max = Integer.MAX_VALUE, message = Constants.QUEUE_SIZE_INVALID)
    private Integer queueCapacity = 30;

    @NotNull(message = Constants.TIME_OUT_INVALID)
    private Boolean allowCoreThreadTimeout = false;

    @NotNull(message = Constants.KEEP_ALIVE_INVALID)
    private Duration keepAliveSeconds = Duration.ofSeconds(30);
}
