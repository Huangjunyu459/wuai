package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/article")
public class ArticleController {

    /**
     * 注入 articleService
     */
    @Autowired
    private ArticleServiceImpl articleService;


    /**
     * 上传文章
     *
     * @param article 文章实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("save")
    public Result1 save(Article article) {
        if (articleService.save(article)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取文章
     *
     * @param id 文章 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(Long id) {
        Article article = articleService.getById(id);
        if (article != null) {
            return Result1.success().data("article", article);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 更新文章信息
     *
     * @param article 文章实体
     * @return 返回的结果 msg
     */
    @PostMapping("updateById")
    public Result1 updateById(Article article) {
        if (articleService.updateById(article)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }


    /**
     * 根据 id 删除文章
     *
     * @param id 文章 id
     * @return 返回的结果 msg
     */
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
        if (articleService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查找所有已审核的文章
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllArticleExamine")
    public Result1 findAllArticleExamine() {
        List<Article> articleList = articleService.findAllArticleExamine();
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 查找所有已审核的文章
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllArticleNoExamine")
    public Result1 findAllArticleNoExamine() {
        List<Article> articleList = articleService.findAllArticleNoExamine();
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 审核功能
     *
     * @param id 视频的 id
     * @return 返回结果 msg
     */
    @GetMapping("examine")
    public Result1 examine(Long id) {
        if (articleService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 文章标题查询
     *
     * @param title 文章标题
     * @return 返回的结果 msg
     */
    @GetMapping("findArticleByTitle")
    public Result1 findArticleByTitle(String title) {
        List<Article> articleList = articleService.findArticleByTitle(title);
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 文章点赞
     *
     * @param id 文章的 id
     * @return 返回的结果 msg
     */
    @GetMapping("likes")
    public Result1 likes(Long id) {
        if (articleService.likes(id)) {
            return Result1.success().setMessage("点赞成功");
        } else {
            return Result1.fail().setMessage("点赞失败");
        }
    }


    /**
     * 查询已删除的文章
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Article> articleList = articleService.findIsDelete();
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 文章分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable articleIPage = articleService.pagingQuery(index);
        if (articleIPage != null) {
            return Result1.success().data("articleIPage", articleIPage);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }


    /**
     * 根据 壁纸 id 查询所属分类
     *
     * @param aid 壁纸的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByAid")
    public Result1 findCategoryNameByAid(Long aid) {
        String categoryName = articleService.findCategoryNameByAid(aid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

