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
 * <p>
 * Druid数据库配置类
 * @Primary 在同样的Datasource中，优先被使用
 */
@Configuration
@Primary
public class DruidDataSourceConfig extends DataSourceProperties {

    /**
     * 读取配置文件的 url
     */
    @Value("${spring.datasource.url}")
    private String dbUrl;

    /**
     * 读取配置文件的 username
     */
    @Value("${spring.datasource.username}")
    private String username;


    /**
     * 读取配置文件的 password
     */
    @Value("${spring.datasource.password}")
    private String password;


    /**
     * 读取配置文件的 driverClassName
     */
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * @return 返回一个数据源对象
     */
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
