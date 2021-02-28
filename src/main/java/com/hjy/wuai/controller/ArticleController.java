package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
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

    @Autowired
    private ArticleServiceImpl articleService;

    @PostMapping("upload")
    public String upload(Article article) {
        return articleService.save(article) == true ? "admin" : "fail";
    }

    @GetMapping("getById")
    public String getById(Long id) {
        Article article = articleService.getById(id);
        return "admin";
    }

    @PostMapping("updateById")
    public String updateById(Article article) {
        return articleService.updateById(article) == true ? "admin" : "fail";
    }

    @GetMapping("removeById")
    public String removeById(Long id) {
        return articleService.removeById(id) == true ? "admin" : "fail";
    }

    @GetMapping("/findAllArticle")
    public String findAllArticle() {
        List<Article> articleList = articleService.findAllArticle();
        return "admin";
    }

    @GetMapping("findArticleByTitle")
    public String findArticleByTitle(String title) {
        List<Article> article = articleService.findArticleByTitle(title);
        return "admin";
    }

    @GetMapping("likes")
    public String likes(Long id) {
        return articleService.likes(id) == true ? "admin" : "fail";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<Article> isDelete = articleService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable articleIPage = articleService.pagingQuery(index);
        System.out.println(articleIPage);
        return "admin";
    }


}

