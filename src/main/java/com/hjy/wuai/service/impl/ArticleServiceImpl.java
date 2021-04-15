package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.ArticleMapper;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.service.ArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 注入 articleMapper
     */
    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 上传作品
     *
     * @param entity 实体类
     * @return 返回上传的结果
     */
    @Override
    public boolean save(Article entity) {
        Article article = new Article();
        article.setTitle(entity.getTitle());
        article.setAuthorId(entity.getAuthorId());
        article.setCategoryId(5);
        article.setContent(entity.getContent());
        article.setArticleCover(entity.getArticleCover());
        return articleMapper.insert(article) == 1;
    }


    /**
     * 根据 id 获取作品（过审）
     *
     * @param id 作品 id
     * @return 返回结果
     */
    @Override
    public Article getById(Serializable id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return articleMapper.selectOne(wrapper);
    }

    /**
     * 根据 id 获取作品（未过审）
     *
     * @param id 作品 id
     * @return 返回结果
     */
    public Article getByIdNoExamine(Serializable id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).eq("id", id);
        return articleMapper.selectOne(wrapper);
    }


    /**
     * 作品更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 作品实体
     * @return
     */
    @Override
    public boolean updateById(Article entity) {

        //  获取要更新的用户
        Article article = getById(entity.getId());
        article.setTitle(entity.getTitle());
        article.setContent(entity.getContent());
        article.setArticleCover(entity.getArticleCover());
        article.setLove(entity.getLove());
        return articleMapper.updateById(article) == 1;
    }


    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return articleMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有已审核的作品
     *
     * @return
     */
    @Override
    public List<Article> findAllArticleExamine() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return articleMapper.selectList(wrapper);
    }


    /**
     * 查询所有未审核的作品
     *
     * @return
     */
    @Override
    public List<Article> findAllArticleNoExamine() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return articleMapper.selectList(wrapper);
    }


    /**
     * 审核功能
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(String id) {
        Article article = articleMapper.selectById(id);
        article.setExamine(1);
        return articleMapper.updateById(article) == 1;
    }


    /**
     * 根据 作品名 模糊查询（过审）
     *
     * @param title
     * @return
     */
    @Override
    public List<Article> findArticleByTitleExamine(String title) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("title", title).eq("examine", 1);
        return articleMapper.selectList(wrapper);
    }

    /**
     * 根据 作品名 模糊查询（未过审）
     *
     * @param title
     * @return
     */
    @Override
    public List<Article> findArticleByTitleNoExamine(String title) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("title", title).eq("examine", 0);
        return articleMapper.selectList(wrapper);
    }

    /**
     * 查询最新的八篇文章
     *
     * @return 返回的结果
     */
    @Override
    public List<Article> findEightArticle() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 8");
        return articleMapper.selectList(wrapper);
    }

    /**
     * 查询最新的十六篇文章
     *
     * @return 返回的结果
     */
    @Override
    public List<Article> findSixthArticle() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 16");
        return articleMapper.selectList(wrapper);
    }

    /**
     * 根据 作品名 模糊查询（已删除）
     *
     * @param title
     * @return
     */
    @Override
    public List<Article> findArticleByTitleIsDelete(String title) {
        title = "%" + title + "%";
        return articleMapper.findArticleByTitleIsDelete(title);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(String id) {
        Article article = articleMapper.selectById(id);
        article.setLove(article.getLove() + 1);
        return articleMapper.updateById(article) == 1;
    }


    /**
     * 查询已删除的作品
     *
     * @return
     */
    @Override
    public List<Article> findIsDelete() {
        return articleMapper.findIsDelete();
    }

    /**
     * 分页查询（过审）
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Article> pagingQueryExamine(String title, Integer index, Integer size) {
        IPage<Article> page = new Page<>(index, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).like("title", title);
        IPage<Article> articleIPage = articleMapper.selectPage(page, wrapper);
        return articleIPage;
    }

    /**
     * 分页查询（未过审）
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Article> pagingQueryNoExamine(String title, Integer index, Integer size) {
        IPage<Article> page = new Page<>(index, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).like("title", title);
        IPage<Article> articleIPage = articleMapper.selectPage(page, wrapper);
        return articleIPage;
    }

    /**
     * 分页查询（已删除）
     *
     * @param index
     * @return
     */
    @Override
    public List<Article> pagingQueryIsDelete(String title, Integer index, Integer size) {
        title = "%" + title + "%";
        return articleMapper.pagingQueryIsDelete(title, index, size);
    }


    /**
     * 根据 文章的 id 查询 所属的分类名
     *
     * @param aid 文章 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByAid(String aid) {
        return articleMapper.findCategoryNameByAid(aid);
    }

}
