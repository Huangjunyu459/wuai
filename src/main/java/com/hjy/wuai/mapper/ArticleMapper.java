package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Article;
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
            "WHERE article.id = #{aid}")
    String findCategoryNameByAid(Long aid);

}
