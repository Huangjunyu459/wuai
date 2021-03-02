package com.hjy.wuai.pojo;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hjy
 * @date 2021/3/1 0001,上午 11:19
 * @email 541605007@qq.com
 */
@Data
public class Result1 {

    public static final Integer DEFAULT_FAILED_CODE = 9999;
    public static final Integer DEFAULT_SUCCESS_CODE = 0;

    /**
     * 状态码
     */
    private Integer statue;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回的数据类
     */
    private Map<String, Object> data = new HashMap<>();


    /**
     * 成功的方法
     *
     * @return 返回  result1 实体类
     */
    public static Result1 success() {
        Result1 result1 = new Result1();
        result1.setCode(Result1.DEFAULT_SUCCESS_CODE);
        result1.setMsg("成功");
        return result1;
    }


    /**
     * 失败的方法
     *
     * @return 返回  result1 实体类
     */
    public static Result1 fail() {
        Result1 result1 = new Result1();
        result1.setCode(Result1.DEFAULT_FAILED_CODE);
        result1.setMsg("失败");
        return result1;
    }


    /**
     * 设置状态码
     *
     * @param code 状态码
     * @return 返回  result1 实体类
     */
    public Result1 setCode(Integer code) {
        this.setStatue(code);
        return this;
    }


    /**
     * 设置返回的 msg
     *
     * @param msg 消息
     * @return 返回  result1 实体类
     */
    public Result1 setMessage(String msg) {
        this.setMsg(msg);
        return this;
    }


    /**
     * 设置返回的数据（以键值对返回）
     *
     * @param key   key
     * @param value key 对应 值
     * @return 返回  result1 实体类
     */
    public Result1 data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    /**
     * 设置返回的数据（以 hashMap 返回）
     *
     * @param map 哈希表
     * @return 返回  result1 实体类
     */
    public Result1 data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
