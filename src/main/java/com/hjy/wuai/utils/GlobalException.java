package com.hjy.wuai.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author： hjy
 * @date： 2021/2/19 0019,下午 20:27
 * @email: 541605007@qq.com
 * <p>
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public String doException(Exception e) {
        if (e instanceof AuthorizationException) {

        }
        return null;
    }
}
