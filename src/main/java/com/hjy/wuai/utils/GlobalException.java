package com.hjy.wuai.utils;

import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

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
            return "shiro认证器异常";
        } else if (e instanceof MaxUploadSizeExceededException) {
            return "文件大小超过限制";
        } else if (e instanceof DataIntegrityViolationException) {
            return "数据完整性违规异常";
        }
        return null;
    }
}
