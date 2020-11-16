package com.clouds3n.common.torn.autoconfigure.system;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.system.properties.StarterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author clouds3n
 * @time 2020-11-14 09:32
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@AutoConfigureOrder(1)
@EnableConfigurationProperties(StarterProperties.class)
public class StarterAutoConfiguration {
    public StarterAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_STARTER_AUTO_CONFIGURE);
    }
}
