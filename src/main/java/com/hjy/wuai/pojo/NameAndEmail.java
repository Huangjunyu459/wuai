package com.hjy.wuai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hjy
 * @date 2021/2/27 0027,下午 14:52
 * @email 541605007@qq.com
 * <p>
 * 条件查询实体类，综合 用户名 和 邮箱
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameAndEmail implements Serializable {

    /**
     * 评论id
     */
    private String id;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户名
     */
    private String username;


    /**
     * 邮箱
     */
    private String email;

}
