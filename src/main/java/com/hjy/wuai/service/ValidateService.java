package com.hjy.wuai.service;


/**
 * @author hjy
 * @date 2021/4/22 0022,下午 15:48
 * @email 541605007@qq.com
 */
public interface ValidateService {

    /**
     * 邮箱发送验证码
     *
     * @param email 目的邮箱
     * @return 返回的结果
     */
    boolean sendEmailVCode(String email);


    /**
     * 邮箱发送注册码
     *
     * @param email 目的邮箱
     * @return 返回的结果
     */
    boolean sendEmailRCode(String email);

    /**
     * 检测注册码
     *
     * @param RCode 注册码
     * @param email 邮箱
     * @return 返回的结果
     */
    boolean checkRCode(String email, String RCode);


    /**
     * 检测验证码
     *
     * @param VCode 验证码
     * @param email 邮箱
     * @return 返回的结果
     */
    boolean checkVCode(String email, String VCode);


}
