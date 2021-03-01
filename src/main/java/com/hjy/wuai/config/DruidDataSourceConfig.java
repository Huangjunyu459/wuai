package com.hjy.wuai.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author hjy
 * @date 2021/2/18 0018,下午 14:04
 * @email 541605007@qq.com
 */
@Configuration
@Primary    //  在同样的Datasource中，优先被使用
public class DruidDataSourceConfig extends DataSourceProperties {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.dbUrl);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        druidDataSource.setDriverClassName(this.driverClassName);
        return druidDataSource;
    }


}
