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
 * @since 2021-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Wallpaper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局唯一标识id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 壁纸标题
     */
    private String title;

    /**
     * 上传的作者id
     */
    private String authorId;

    /**
     * 分类id
     */
    private Integer categoryId;


    /**
     * oos的路径
     */
    private String ossSrc;

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
     * 点赞人数
     */
    private Integer love;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;


    /**
     * 审核状态
     */
    private Integer examine;

    /**
     * 是否会员专属
     */
    private Boolean isVip;

}
