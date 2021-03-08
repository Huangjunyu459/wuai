package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询已删除的作品
     *
     * @return 返回已被删除的作品集合
     */
    @Select("select * from article where is_delete = 1")
    List<Article> findIsDelete();


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param aid 视频 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN article " +
            "ON article.category_id = category.id " +
            "WHERE article.examine = 1 AND article.id = #{aid}")
    String findCategoryNameByAid(String aid);

    /**
     * 根据名字模糊查找已删除的文章
     *
     * @param title 文章标题
     * @return 返回的结果
     */
    @Select("SELECT * FROM article WHERE is_delete = 1 AND title LIKE #{title} ")
    List<Article> findArticleByTitleIsDelete(String title);

    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param title
     * @return
     */
    @Select("select * from article where is_delete = 1 AND title like #{title} limit #{index} , #{size}")
    List<Article> pagingQueryIsDelete(String title,Integer index, Integer size);

}
