package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询已删除的用户
     *
     * @return 返回已被删除的用户集合
     */
    @Select("select * from user where is_delete = 1")
    List<User> findIsDelete();



}
