package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.CategoryMapper;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 注入 categoryMapper
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据 id 删除分类
     *
     * @param id 分类 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return categoryMapper.deleteById(id) == 1;
    }


    /**
     * 新建角色
     *
     * @param entity 角色实体
     * @return
     */
    @Override
    public boolean save(Category entity) {
        if (findCategoryByIdAll(entity.getId()).size() != 0) {
            return false;
        } else {
            return categoryMapper.insert(entity) == 1;
        }
    }

    /**
     * 查询所有分类
     *
     * @return 返回的结果
     */
    @Override
    public List<Category> findAllCategory() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        return categoryMapper.selectList(wrapper);
    }


    /**
     * 根据id查找所有的分类
     *
     * @param id 分类id
     * @return 返回的结果
     */
    @Override
    public List<Category> findCategoryByIdAll(String id) {
        return categoryMapper.findCategoryByIdAll(id);
    }

    /**
     * 根据分类名 模糊查询
     *
     * @param CategoryName 分类名
     * @return 返回的结果
     */
    @Override
    public List<Category> findCategoryByCategoryName(String CategoryName) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.like("category_name", CategoryName);
        return categoryMapper.selectList(wrapper);
    }


    /**
     * 角色更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 角色实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Category entity) {

        //  获取要更新的角色
        Category category = getById(entity.getId());
        category.setCategoryName(entity.getCategoryName());
        return categoryMapper.updateById(category) == 1;
    }

    /**
     * 根据 id 获取分类
     *
     * @param id 分类 id
     * @return 返回的结果
     */
    @Override
    public Category getById(Serializable id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 根据分类名 模糊查询（已删除的分类）
     *
     * @param CategoryName 分类名
     * @return 返回的结果
     */
    @Override
    public List<Category> findCategoryByCategoryNameIsDelete(String CategoryName) {
        CategoryName = "%" + CategoryName + "%";
        return categoryMapper.findCategoryByCategoryNameIsDelete(CategoryName);
    }

    /**
     * 查询已删除的分类
     *
     * @return 返回的结果
     */
    @Override
    public List<Category> findIsDelete() {
        return categoryMapper.findIsDelete();
    }


    /**
     * 分页查询
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Category> pagingQuery(String categoryName, Integer index, Integer size) {
        IPage<Category> page = new Page<>(index, size);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.like("category_name", categoryName);
        IPage<Category> categoryIPage = categoryMapper.selectPage(page, wrapper);
        return categoryIPage;
    }

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<Category> pagingQueryIsDelete(String categoryName, Integer index, Integer size) {
        categoryName = "%" + categoryName + "%";
        List<Category> categoryList = categoryMapper.pagingQueryIsDelete(categoryName,index, size);
        return categoryList;
    }


}
