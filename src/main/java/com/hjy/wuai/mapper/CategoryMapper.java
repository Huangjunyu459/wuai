package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>AdminMapper
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询已删除的分类
     *
     * @return 返回已被删除的分类
     * 集合
     */
    @Select("select * from category where is_delete = 1")
    List<Category> findIsDelete();

    /**
     * 根据id查找所有的分类
     *
     * @param id 用户名
     * @return 返回的结果
     */
    @Select("SELECT * FROM category WHERE id = #{id} ")
    List<Category> findCategoryByIdAll(String id);

    /**
     * 根据名字模糊查找已删除的分类
     *
     * @param CategoryName
     * @return 返回的结果
     */
    @Select("SELECT * FROM category WHERE is_delete = 1 AND category_name LIKE #{CategoryName} ")
    List<Category> findCategoryByCategoryNameIsDelete(String CategoryName);


    /**
     * 分页查询已删除的分类
     *
     * @param index
     * @param size
     * @param categoryName
     * @return
     */
    @Select("select * from Category where is_delete = 1 AND category_name like #{categoryName} limit #{index} , #{size}")
    List<Category> pagingQueryIsDelete(String categoryName, Integer index, Integer size);

}
