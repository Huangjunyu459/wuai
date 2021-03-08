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
 * @since 2021-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐值
     */
    private String passwordSalt;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户头像连接地址
     */
    private String avatar;

    /**
     * 微信id
     */
    private String openId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

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
     * 用户邮箱
     */
    private String email;

    /**
     * 用户级别
     */
    private Integer state;

    /**
     * 签到状态，默认为0，已签到为1，每过晚上12点刷新为 0
     */
    private Integer qiandao;

}
