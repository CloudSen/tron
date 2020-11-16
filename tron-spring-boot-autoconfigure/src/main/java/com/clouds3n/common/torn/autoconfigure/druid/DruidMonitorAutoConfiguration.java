package com.clouds3n.common.torn.autoconfigure.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.druid.properties.DruidMonitorProperties;
import com.clouds3n.common.torn.autoconfigure.system.StarterAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid Monitor
 *
 * @author CloudS3n
 * @date 2020-06-27 18:10
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DruidMonitorProperties.class)
@ConditionalOnClass(WebStatFilter.class)
@ConditionalOnProperty(name = "config.starter.enable-druid-monitor", havingValue = "true")
@AutoConfigureAfter(StarterAutoConfiguration.class)
public class DruidMonitorAutoConfiguration {

    private static final String EXCLUSIONS = "*.html,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";

    public DruidMonitorAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_DRUID_MONITOR_AUTO_CONFIGURE);
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet(DruidMonitorProperties druidMonitorProperties) {
        log.info(AutoConfigConstants.LOADING_DRUID_MONITOR_SERVLET);
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), druidMonitorProperties.getServletUrl());
        servletRegistrationBean.addInitParameter("allow", druidMonitorProperties.getDruidAllowIps());
        servletRegistrationBean.addInitParameter("loginUsername", druidMonitorProperties.getDruidUsername());
        servletRegistrationBean.addInitParameter("loginPassword", druidMonitorProperties.getDruidPassword());
        servletRegistrationBean.addInitParameter("resetEnable", druidMonitorProperties.getDruidResettable());
        return servletRegistrationBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        log.info(AutoConfigConstants.LOADING_DRUID_MONITOR_FILTER);
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", EXCLUSIONS);
        return filterRegistrationBean;
    }
}
