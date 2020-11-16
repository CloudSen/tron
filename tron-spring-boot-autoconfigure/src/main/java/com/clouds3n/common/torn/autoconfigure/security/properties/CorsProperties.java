package com.clouds3n.common.torn.autoconfigure.security.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.List;

/**
 * @author CloudS3n
 * @date 2019/6/29 13:15
 */
@Data
@Accessors(chain = true)
@Validated
@Component
@ConfigurationProperties(prefix = "config.security.cors", ignoreUnknownFields = false)
public class CorsProperties {

    @NotEmpty
    private List<String> allowedHeaders = Collections.singletonList("*");

    @NotEmpty
    private List<String> allowedOrigins = Collections.singletonList("*");

    @NotEmpty
    private List<String> allowedMethods = Collections.singletonList("*");

    @NotBlank
    private String path = "/**";
}
