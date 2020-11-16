package com.clouds3n.common.torn.autoconfigure.mybatis.transaction;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.mybatis.CustomMybatisPlusAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author CloudS3n
 * @date 2019/12/2 15:20
 */
@Slf4j
@Configuration
@ConditionalOnBean(CustomMybatisPlusAutoConfiguration.class)
@AutoConfigureAfter(CustomMybatisPlusAutoConfiguration.class)
@EnableTransactionManagement
public class TransactionAutoConfiguration {

    public TransactionAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_TRANSACTION_AUTO_CONFIGURE);
    }

    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        log.info(AutoConfigConstants.LOADING_TRANSACTION_MANAGER);
        return new DataSourceTransactionManager(dataSource);
    }
}
