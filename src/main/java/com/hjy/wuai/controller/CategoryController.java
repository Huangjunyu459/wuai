package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-27
 */
@RestController
@RequestMapping("/category")
@CrossOrigin(originPatterns = "*",allowCredentials = "true",allowedHeaders = "*",methods = {})
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 新建分类
     *
     * @param category 视频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addCategory")
    public Result1 addCategory(@RequestBody Category category) {
        if (categoryService.save(category)) {
            return Result1.success().setMessage("新建分类成功");
        } else {
            return Result1.fail().setMessage("新建分类失败");
        }
    }


    /**
     * 根据 id 获取分类
     *
     * @param id 分类 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Category category = categoryService.getById(id);
        if (category != null) {
            return Result1.success().data("category", category);
        } else {
            return Result1.fail().setMessage("分类不存在");
        }
    }

    /**
     * 查找所有角色
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllCategory")
    public Result1 findAllCategory() {
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.size() != 0) {
            return Result1.success().data("categoryList", categoryList);
        } else {
            return Result1.fail().setMessage("分类不存在");
        }
    }


    /**
     * 根据 分类名模糊查询
     *
     * @param categoryName 分类名
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryByCategoryName")
    public Result1 findCategoryByCategoryName(String categoryName) {
        List<Category> categoryList = categoryService.findCategoryByCategoryName(categoryName);
        if (categoryList.size() != 0) {
            return Result1.success().data("categoryList", categoryList);
        } else {
            return Result1.fail().setMessage("分类不存在");
        }
    }


    /**
     * 更新分类信息
     *
     * @param category 分类实体类
     * @return 返回的结果 msg
     */
    @PostMapping("updateCategoryById")
    public Result1 updateCategoryById(@RequestBody Category category) {
        if (categoryService.updateById(category)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }


    /**
     * 根据 id 删除分类
     *
     * @param id 分类的id
     * @return 返回的结果 msg
     */
    @DeleteMapping("removeCategoryById")
    public Result1 removeCategoryById(String id) {
        if (categoryService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查询已删除的分类
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Category> categoryList = categoryService.findIsDelete();
        if (categoryList.size() != 0) {
            return Result1.success().data("categoryList", categoryList);
        } else {
            return Result1.fail().setMessage("分类不存在");
        }
    }


    /**
     * 根据用户名模糊查询已删除分类
     *
     * @param categoryName 分类名
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryByCategoryNameIsDelete")
    public Result1 findCategoryByCategoryNameIsDelete(String categoryName) {
        List<Category> categoryList = categoryService.findCategoryByCategoryNameIsDelete(categoryName);
        if (categoryList.size() == 0) {
            return Result1.fail().setMessage("分类不存在");
        } else {
            return Result1.success().data("categoryList", categoryList);
        }
    }


    /**
     * 分类的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(String categoryName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable categoryIPage = categoryService.pagingQuery(categoryName,index, size);
        if (categoryIPage != null) {
            return Result1.success().data("categoryIPage", categoryIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 分类的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String categoryName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Category> categoryList = categoryService.pagingQueryIsDelete(categoryName,index, size);
        if (categoryList.size() != 0) {
            return Result1.success().data("categoryList", categoryList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

}

