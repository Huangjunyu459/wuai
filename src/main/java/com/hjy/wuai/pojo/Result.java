package com.hjy.wuai.pojo;

import lombok.Data;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 9:46
 * @email: 541605007@qq.com
 * 封装的结果集对象
 */
@Data
public class Result<T> {

    public static final String DEFAULT_FAILED_CODE = "9998";
    public static final String DEFAULT_FAULT_CODE = "9999";
    public static final String DEFAULT_SUCCESS_CODE = "0";

    /**
     * 错误返回时的错误码
     */
    private String code;

    /**
     * 错误返回时的错误信息
     */
    private String message;

    /**
     * status为SC_OK时的JSON字符串
     */
    private T data;

    /**
     * 构造函数(成功返回时使用)
     *
     * @param object 支持转换JSON格式的Java对象
     */
    public Result(T object) {

        this.code = DEFAULT_SUCCESS_CODE;
        this.message = "";
        this.data = object;
    }

    /**
     * 构造函数(失败返回时使用)
     */
    public Result(String code, String message) {

        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static <T> Result<T> ok() {
        return new Result<>(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> failed(String message) {

        return new Result<>(DEFAULT_FAILED_CODE, message);
    }

    public static <T> Result<T> failed(String code, String message) {

        return new Result<>(code, message);
    }

    public static <T> Result<T> fault(String message) {

        return new Result<>(DEFAULT_FAULT_CODE, message);
    }

    public static <T> Result<T> fault(String code, String message) {

        return new Result<>(code, message);
    }
}
