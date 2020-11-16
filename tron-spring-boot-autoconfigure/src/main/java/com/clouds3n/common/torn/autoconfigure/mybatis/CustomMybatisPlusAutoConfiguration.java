package com.clouds3n.common.torn.autoconfigure.mybatis;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author CloudS3n
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "config.starter.enable-mybatis-plus", havingValue = "true")
@AutoConfigureAfter(DynamicDataSourceAutoConfiguration.class)
public class CustomMybatisPlusAutoConfiguration {

    public CustomMybatisPlusAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_MYBATIS_PLUS_AUTO_CONFIGURE);
    }

    @Bean("mybatisSqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        Resource[] resources = new PathMatchingResourcePatternResolver()
            .getResources("classpath*:mapper/**/*.xml");
        log.info(AutoConfigConstants.MYBATIS_PLUS_MAPPERS, Arrays.toString(resources).replaceAll(",", "\n"));
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(resources);
        factoryBean.setPlugins(paginationInterceptor());
        factoryBean.setFailFast(true);
        factoryBean.setGlobalConfig(new GlobalConfig().setDbConfig(new GlobalConfig.DbConfig().setKeyGenerator(new OracleKeyGenerator())));
        return factoryBean.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor pagination = new MybatisPlusInterceptor();
        pagination.addInnerInterceptor(new PaginationInnerInterceptor());
        return pagination;
    }
}
