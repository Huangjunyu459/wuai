package com.hjy.wuai.config;

import com.aliyun.oss.OSSClient;
import com.hjy.wuai.utils.OssConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjy
 * @date 2021/2/28 0028,上午 9:48
 * @email 541605007@qq.com
 * OSS客户端配置类
 */
@Configuration
public class OssConfig {

    /**
     * 定义ossClient bean对象
     */
    @Bean
    public OSSClient ossClient() {
        return new OSSClient(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
    }
}
