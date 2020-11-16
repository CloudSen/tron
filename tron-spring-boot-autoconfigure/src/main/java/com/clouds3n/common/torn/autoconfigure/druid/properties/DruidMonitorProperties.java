package com.clouds3n.common.torn.autoconfigure.druid.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author CloudS3n
 * @date 2020-06-27 18:10
 */
@Validated
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "config.druid", ignoreUnknownFields = false)
public class DruidMonitorProperties {

    @NotBlank
    private String druidUsername = "druid";

    @NotBlank
    private String druidPassword = "123456";

    @NotBlank
    private String druidResettable = "true";

    @NotBlank
    private String druidAllowIps = "*";

    @NotBlank
    private String servletUrl = "/druid/*";
}
