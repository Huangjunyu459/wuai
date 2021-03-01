package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-27
 */
public interface ArticleService extends IService<Article> {


    /**
     * 查询所有作品
     *
     * @return
     */
    List<Article> findAllArticle();


    /**
     * 根据 作品名 模糊查询
     *
     * @param title
     * @return
     */
    List<Article> findArticleByTitle(String title);

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    boolean likes(Long id);

    /**
     * 查询已删除的作品
     *
     * @return
     */
    List<Article> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    IPage<Article> pagingQuery(Integer index);

    /**
     * 根据 文章的 id 查询 所属的分类名
     *
     * @param aid 文章 id
     * @return 分类名称
     */
    String findCategoryNameByAid(Long aid);


}
