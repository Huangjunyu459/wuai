package com.hjy.wuai.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author： hjy
 * @date： 2021/2/18 0018,下午 14:47
 * @email: 541605007@qq.com
 * <p>
 * 自定义实现类 MyMetaObjectHandler
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时的填充策略
     *
     * @param metaObject 传入的参数
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        /**
         * 给数据库字段为 create_time 设置一个 Date,mybatisplus的新版本方法
         * this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
         * this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
         */
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }


    /**
     * 更新时的填充策略
     *
     * @param metaObject 传入的参数
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        /**
         * 给数据库字段为 update_time 设置一个 Date
         * this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
         */
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
