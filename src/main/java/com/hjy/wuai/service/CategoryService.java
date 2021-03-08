package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Roles;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-27
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询所有分类
     *
     * @return 返回的结果
     */
    List<Category> findAllCategory();

    /**
     * 查询已删除的分类
     *
     * @return 返回的结果
     */
    List<Category> findIsDelete();


    /**
     * 根据名字模糊查找所有的角色
     *
     * @param id 角色 id
     * @return 返回的结果
     */
    List<Category> findCategoryByIdAll(String id);


    /**
     * 根据分类名 模糊查询
     *
     * @param CategoryName 分类名
     * @return 返回的结果
     */
    List<Category> findCategoryByCategoryName(String CategoryName);

    /**
     * 根据分类名 模糊查询（已删除的分类）
     *
     * @param CategoryName 分类名
     * @return 返回的结果
     */
    List<Category> findCategoryByCategoryNameIsDelete(String CategoryName);

    /**
     * 分页查询
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Category> pagingQuery(String categoryName,Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Category> pagingQueryIsDelete(String categoryName,Integer index, Integer size);

}
