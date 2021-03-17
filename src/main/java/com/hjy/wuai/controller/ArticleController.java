package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.ArticleServiceImpl;
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
@RequestMapping("/article")
@CrossOrigin
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
    @PostMapping("addArticle")
    public Result1 addArticle(@RequestBody Article article) {
        if (articleService.save(article)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取文章（过审）
     *
     * @param id 文章 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Article article = articleService.getById(id);
        if (article != null) {
            return Result1.success().data("article", article);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }

    /**
     * 根据 id 获取文章（未过审）
     *
     * @param id 文章 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Article article = articleService.getByIdNoExamine(id);
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
    @PostMapping("updateArticleById")
    public Result1 updateArticleById(@RequestBody Article article) {
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
    @DeleteMapping("removeArticleById")
    public Result1 removeArticleById(String id) {
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
    public Result1 examine(String id) {
        if (articleService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 文章标题查询（已过审）
     *
     * @param title 文章标题
     * @return 返回的结果 msg
     */
    @GetMapping("findArticleByTitleExamine")
    public Result1 findArticleByTitleExamine(String title) {
        List<Article> articleList = articleService.findArticleByTitleExamine(title);
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }

    /**
     * 根据 文章标题查询（未过审）
     *
     * @param title 文章标题
     * @return 返回的结果 msg
     */
    @GetMapping("findArticleByTitleNoExamine")
    public Result1 findArticleByTitleNoExamine(String title) {
        List<Article> articleList = articleService.findArticleByTitleNoExamine(title);
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }

    /**
     * 根据 文章标题查询（已删除）
     *
     * @param title 文章标题
     * @return 返回的结果 msg
     */
    @GetMapping("findArticleByTitleIsDelete")
    public Result1 findArticleByTitleIsDelete(String title) {
        List<Article> articleList = articleService.findArticleByTitleIsDelete(title);
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }

    /**
     * 查询最新的八篇文章
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findEightArticle")
    public Result1 findEightArticle() {
        List<Article> articleList = articleService.findEightArticle();
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("文章不存在");
        }
    }

    /**
     * 查询最新的十六篇文章
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findSixthArticle")
    public Result1 findSixthArticle() {
        List<Article> articleList = articleService.findSixthArticle();
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
    public Result1 likes(String id) {
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
     * 壁纸的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable articleIPage = articleService.pagingQueryExamine(title, index, size);
        if (articleIPage != null) {
            return Result1.success().data("articleIPage", articleIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 壁纸的分页查询（未过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryNoExamine")
    public Result1 pagingQueryNoExamine(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable articleIPage = articleService.pagingQueryNoExamine(title, index, size);
        if (articleIPage != null) {
            return Result1.success().data("articleIPage", articleIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 壁纸的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Article> articleList = articleService.pagingQueryIsDelete(title, index, size);
        if (articleList.size() != 0) {
            return Result1.success().data("articleList", articleList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据 壁纸 id 查询所属分类
     *
     * @param aid 壁纸的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByAid")
    public Result1 findCategoryNameByAid(@PathVariable String aid) {
        String categoryName = articleService.findCategoryNameByAid(aid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

