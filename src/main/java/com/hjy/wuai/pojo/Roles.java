package com.hjy.wuai.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局唯一标识id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名字
     */
    private String roleName;

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


}
