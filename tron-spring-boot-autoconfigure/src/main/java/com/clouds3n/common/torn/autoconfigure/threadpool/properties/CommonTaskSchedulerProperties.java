package com.clouds3n.common.torn.autoconfigure.threadpool.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author CloudS3n
 * @date 2020-06-25 16:05
 */
@Validated
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "config.thread-pool.scheduler", ignoreUnknownFields = false)
public class CommonTaskSchedulerProperties {

    @NotBlank(message = Constants.SCHEDULER_THREAD_NAME_PREFIX_INVALID)
    private String threadNamePrefix = "common-scheduler-";

    @Range(min = 20, max = Integer.MAX_VALUE, message = Constants.POOL_SIZE_INVALID)
    private Integer poolSize = 20;
}
