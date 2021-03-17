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
 * @since 2021-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局唯一标识id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 描述
     */
    private String description;

    /**
     * 游戏存储在百度盘的链接
     */
    private String bdSrc;

    /**
     * 百度盘的提取码
     */
    private String bdCode;

    /**
     * 解压码
     */
    private String dCode;

    /**
     * 游戏封面图
     */
    private String gameCover;

    /**
     * 上传的用户的id
     */
    private Long authorId;

    /**
     * 分类的id
     */
    private Integer categoryId;

    /**
     * 点赞
     */
    private Integer love;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 版本号
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 审核状态
     */
    private Integer examine;

}
