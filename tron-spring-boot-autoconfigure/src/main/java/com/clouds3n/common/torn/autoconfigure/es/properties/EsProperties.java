package com.clouds3n.common.torn.autoconfigure.es.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author CloudS3n
 * @date 2019/11/28 17:02
 */
@Validated
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "config.es")
public class EsProperties {

    @NotEmpty
    private List<String> addresses = Collections.singletonList("127.0.0.1");

    @NotNull
    @Range(max = Integer.MAX_VALUE)
    private Integer restPort = 9200;

    @NotNull
    @Range(max = Integer.MAX_VALUE)
    private Integer commPort = 9300;

    @NotNull
    private Boolean enableSniff = true;

    @NotEmpty
    private String clusterName = "my-es";
}
