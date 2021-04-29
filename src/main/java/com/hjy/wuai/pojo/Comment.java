package com.hjy.wuai.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author hjy
 * @since 2021-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 所属的文章
     */
    private String articleId;

    /**
     * 评论人id
     */
    private String userId;

    /**
     * 评论用户名
     */
    private String userName;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 回复的人的id
     * 暂时不添加回复功能，该字段被忽略
     */
    @TableField(exist = false)
    private String userIdParent;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 版本号
     */
    @Version
    private Integer version;

    /**
     * 审核状态
     */
    private Integer examine;

}
