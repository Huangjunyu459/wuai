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

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 上传
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Article entity) {
        return articleMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取作品
     *
     * @param id
     * @return
     */
    @Override
    public Article getById(Serializable id) {
        return articleMapper.selectById(id);
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
        return articleMapper.updateById(article) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return articleMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有作品
     *
     * @return
     */
    @Override
    public List<Article> findAllArticle() {
        return articleMapper.selectList(null);
    }

    /**
     * 根据 作品名 模糊查询
     *
     * @param title
     * @return
     */
    @Override
    public List<Article> findArticleByTitle(String title) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        return articleMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(Long id) {
        Article article = articleMapper.selectById(id);
        article.setLove(article.getLove() + 1);
        return articleMapper.updateById(article) == 1 ? true : false;
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
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Article> pagingQuery(Integer index) {
        IPage<Article> page = new Page<>(index, 5);
        IPage<Article> userIPage = articleMapper.selectPage(page, null);
        return userIPage;
    }

    /**
     * 根据 文章的 id 查询 所属的分类名
     *
     * @param aid 文章 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByAid(Long aid) {
        return articleMapper.findCategoryNameByAid(aid);
    }


}
