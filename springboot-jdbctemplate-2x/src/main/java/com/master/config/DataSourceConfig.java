package com.master.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by tuonioooo.
 * User: tony
 * Date: 2018-5-15
 * Time: 10:25
 * info: 配置数据源
 */
@Configuration
public class DataSourceConfig {


    /**
     * 数据源配置对象
     * @Primary 表示默认的对象，Autowire可注入，不是默认的得明确名称注入
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    @Qualifier("dataSource")//按照名称装配实例
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {//主数据源
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate (@Qualifier("dataSource")  DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }


    //==============================================

    @Bean
    @ConfigurationProperties("spring.datasource.cloud.mysql")
    public DataSourceProperties cloudDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "cloudDataSource")
    @Qualifier("cloudDataSource")//按照名称装配实例
    @ConfigurationProperties(prefix="spring.datasource.cloud.mysql")
    public DataSource cloudDataSource() {//从数据源
        return cloudDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean(name="cloudJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate (@Qualifier("cloudDataSource")  DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }



}
