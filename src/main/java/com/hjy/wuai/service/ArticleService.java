package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.Wallpaper;

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
     * 查询所有已审核的作品
     *
     * @return 返回的结果
     */
    List<Article> findAllArticleExamine();

    /**
     * 查询所有未审核的作品
     *
     * @return 返回的结果
     */
    List<Article> findAllArticleNoExamine();

    /**
     * 查询已删除的作品
     *
     * @return 返回的结果
     */
    List<Article> findIsDelete();

    /**
     * 根据 作品名 模糊查询（过审）
     *
     * @param title 作品标题
     * @return 返回的结果
     */
    List<Article> findArticleByTitleExamine(String title);

    /**
     * 根据 作品名 模糊查询（未过审）
     *
     * @param title 作品标题
     * @return 返回的结果
     */
    List<Article> findArticleByTitleNoExamine(String title);

    /**
     * 根据 作品名 模糊查询（已删除）
     *
     * @param title 作品标题
     * @return 返回的结果
     */
    List<Article> findArticleByTitleIsDelete(String title);

    /**
     * 审核功能
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    boolean examine(String id);

    /**
     * 点赞功能
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    boolean likes(String id);


    /**
     * 分页查询（过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Article> pagingQueryExamine(String title,Integer index, Integer size);

    /**
     * 分页查询（未过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Article> pagingQueryNoExamine(String title,Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Article> pagingQueryIsDelete(String title,Integer index, Integer size);

    /**
     * 根据 文章的 id 查询 所属的分类名
     *
     * @param aid 文章 id
     * @return 分类名称
     */
    String findCategoryNameByAid(String aid);

}
