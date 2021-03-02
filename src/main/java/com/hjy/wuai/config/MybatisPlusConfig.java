package com.hjy.wuai.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hjy
 * @date 2021/2/18 0018,下午 14:20
 * @email 541605007@qq.com
 * <p>
 * MybatisPlus的配置类
 */

@MapperScan("com.hjy.wuai.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 注册插件
     *
     * @return 返回一个 MybatisPlusInterceptor 对象
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        /**
         * 注册乐观锁插件
         */
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        /**
         * 注册分页插件
         */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }

}
