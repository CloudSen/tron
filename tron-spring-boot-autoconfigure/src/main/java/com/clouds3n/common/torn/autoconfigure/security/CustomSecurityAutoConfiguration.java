package com.clouds3n.common.torn.autoconfigure.security;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.security.properties.CorsProperties;
import com.clouds3n.common.torn.autoconfigure.system.StarterAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author CloudS3n
 * @date 2019/6/29 13:35
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({CorsProperties.class})
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnProperty(name = "config.starter.enable-security", havingValue = "true")
@AutoConfigureAfter(StarterAutoConfiguration.class)
public class CustomSecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    public CustomSecurityAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_SECURITY_AUTO_CONFIGURE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
            .anyRequest()
            .permitAll();
    }

    @Bean
    @ConditionalOnMissingBean
    CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
        log.info(AutoConfigConstants.LOADING_SECURITY_CORS);
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(corsProperties.getAllowedHeaders());
        corsConfiguration.setAllowedOrigins(corsConfiguration.getAllowedOrigins());
        corsConfiguration.setAllowedMethods(corsConfiguration.getAllowedMethods());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsProperties.getPath(), corsConfiguration);
        return source;
    }
}
