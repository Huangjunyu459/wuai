package com.hjy;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.controller.ArticleController;
import com.hjy.wuai.mapper.ArticleMapper;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/27 0027,下午 18:09
 * @email: 541605007@qq.com
 */
@SpringBootTest
public class ArticleTest {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleController articleController;


    @Test
    void testSaveArticle() {
        Article article = new Article();
        article.setTitle("红楼梦2");
        article.setContent("五月天");
        article.setCategoryId(4);
        article.setArticleCover("这是文章封面图片的路径");
        articleController.addArticle(article);
    }

/*    @Test
    public void testGetById() {
        System.out.println(articleController.getById(1365614906300252162L));
        System.out.println(articleService.getById(1365614906300252162L));
    }

    @Test
    void testUpdate() {
        System.out.println(articleController.getById(1365614450597519362L));
        Article article = articleService.getById(1365614450597519362L);
        article.setTitle("三国演义");
        article.setContent("张飞刘备关云长");
        articleController.updateById(article);
    }

    @Test
    void testRemove() {
        System.out.println(articleController.removeById(1365614672983715841L));

    }*/

    @Test
    void testFindAll() {
        System.out.println(articleController.findAllArticleExamine());
        List<Article> allArticle = articleService.findAllArticleExamine();
        Iterator<Article> iterator = allArticle.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

//    @Test
//    void testFindByTitle() {
//        System.out.println(articleController.findArticleByTitle("咒术回战"));
//        List<Article> articles = articleService.findArticleByTitle("红楼");
//        Iterator<Article> iterator = articles.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }

/*    @Test
    void testLikes() {
        articleController.likes(1365614503978364930L);
    }*/

    @Test
    void testFindIsDelete() {
        System.out.println(articleController.findIsDelete());
        List<Article> isDelete = articleService.findIsDelete();
        Iterator<Article> iterator = isDelete.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    void testPage() {
//        IPage<Article> page = new Page<>(1, 5);
//        IPage<Article> articleIPage = articleMapper.selectPage(page, null);
//        articleIPage.getRecords().forEach(System.out::println);

//        articleController.pagingQuery(1);
    }


}


