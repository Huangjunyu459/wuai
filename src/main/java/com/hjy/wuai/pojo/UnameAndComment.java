package com.hjy.wuai.pojo;

import lombok.Data;

/**
 * @author hjy
 * @date 2021/3/2 0002,上午 11:01
 * @email 541605007@qq.com
 * <p>
 * 结果实体类，返回的是 用户名称 和 评论的内容
 */
@Data
public class UnameAndComment {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户的评论
     */
    private String content;
}
