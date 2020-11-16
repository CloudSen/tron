package com.clouds3n.common.torn.autoconfigure.system.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author clouds3n
 * @time 2020-11-14 09:35
 */
@Validated
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "config.starter", ignoreUnknownFields = false)
public class StarterProperties {

    @NotNull
    private Boolean enableScript = true;

    @NotNull
    private Boolean enableThreadPool = true;

    @NotNull
    private Boolean enableDruidMonitor = true;

    @NotNull
    private Boolean enableGlobalExceptionHandler = true;

    @NotNull
    private Boolean enableMybatisPlus = true;

    @NotNull
    private Boolean enableSecurity = true;

    @NotNull
    private Boolean enableEs = true;

    @NotNull
    private Boolean enableMonitor = true;
}
