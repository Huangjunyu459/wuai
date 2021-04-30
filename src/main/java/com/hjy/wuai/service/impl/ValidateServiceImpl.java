package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.hjy.wuai.service.ValidateService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author hjy
 * @date 2021/4/22 0022,下午 15:54
 * @email 541605007@qq.com
 */
@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.mail.host}")
    private String HostName;

    @Value("${spring.mail.username}")
    private String From;

    @Value("${spring.mail.password}")
    private String Password;

    @Value("${spring.mail.properties.default-encoding}")
    private String Default_Encoding;

    @Value("${spring.mail.port}")
    private Integer Port;

    /**
     * 找回密码方法
     *
     * @param email 目的邮箱
     * @return 返回的结果
     */
    @Override
    public boolean sendEmailVCode(String email) {
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            htmlEmail.setHostName(HostName);
            htmlEmail.setCharset(Default_Encoding);
            htmlEmail.addTo(email);
            htmlEmail.setFrom(From);
            htmlEmail.setAuthentication(From, Password);
            htmlEmail.setSubject("邮箱找回密码");
            htmlEmail.setSmtpPort(Port);
            htmlEmail.setSSLOnConnect(true);
            String VCode = ValidateServiceImpl.VerificationCode();
            htmlEmail.setMsg("本次的验证码为：" + VCode + "。有效期为15分钟");
            redisTemplate.opsForValue().set("VCode" + email, VCode, 1800, TimeUnit.SECONDS);
            htmlEmail.send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 邮箱发送用户注册码
     *
     * @param email 目的邮箱
     * @return 返回的结果
     */
    @Override
    public boolean sendEmailRCode(String email) {
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            htmlEmail.setHostName(HostName);
            htmlEmail.setCharset(Default_Encoding);
            htmlEmail.addTo(email);
            htmlEmail.setFrom(From);
            htmlEmail.setSmtpPort(Port);
            htmlEmail.setSSLOnConnect(true);
            htmlEmail.setAuthentication(From, Password);
            htmlEmail.setSubject("吾爱资源分享平台注册码");
            String RCode = ValidateServiceImpl.VerificationCode();
            htmlEmail.setMsg("本次的注册码为：" + RCode + "。有效期为15分钟");
            redisTemplate.opsForValue().set("RCode" + email, RCode, 1800, TimeUnit.SECONDS);
            htmlEmail.send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通知投稿者稿件不合规
     *
     * @param email 投稿者邮箱
     * @return 返回的结果
     */
    @Override
    public boolean sendEmailNoExamine(String email) {
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            htmlEmail.setHostName(HostName);
            htmlEmail.setCharset(Default_Encoding);
            htmlEmail.addTo(email);
            htmlEmail.setFrom(From);
            htmlEmail.setAuthentication(From, Password);
            htmlEmail.setSubject("吾爱资源分享平台");
            htmlEmail.setSmtpPort(Port);
            htmlEmail.setSSLOnConnect(true);
            htmlEmail.setMsg("您本次的投稿内容含有违规内容，已被退回，请遵守投稿规定！");
            htmlEmail.send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检测注册码
     *
     * @param RCode 注册码
     * @return 返回的结果
     */
    @Override
    public boolean checkRCode(String email, String RCode) {
        return RCode.equals(redisTemplate.opsForValue().get("RCode" + email));
    }

    /**
     * 检测验证码
     *
     * @param VCode 验证码
     * @return 返回的结果
     */
    @Override
    public boolean checkVCode(String email, String VCode) {
        return VCode.equals(redisTemplate.opsForValue().get("VCode" + email));
    }

    /**
     * 随机生成6位验证码
     *
     * @return
     */
    public static String VerificationCode() {
        String[] beforeShuffle = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "d", "c", "e", "f", "g", "h", "i", "j",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }


}
