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

    /**
     * 根据名字模糊查找已删除的用户
     *
     * @param username
     * @return 返回的结果
     */
    @Select("SELECT * FROM USER WHERE is_delete = 1 AND username LIKE #{username} ")
    List<User> findUsersByUsernameIsDelete(String username);

    /**
     * 根据名字查找所有的用户
     *
     * @param username 用户名
     * @return 返回的结果
     */
    @Select("SELECT * FROM USER WHERE username = #{username} ")
    List<User> findUsersByUsernameAll(String username);


    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param username
     * @return
     */
    @Select("select * from user where is_delete = 1 AND username like #{username} limit #{index} , #{size}")
    List<User> pagingQueryIsDelete(String username, Integer index, Integer size);




}
