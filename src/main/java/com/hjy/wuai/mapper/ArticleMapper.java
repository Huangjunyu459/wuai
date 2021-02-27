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
     * @return
     */
    @Select("select * from article where is_delete = 1")
    public List<Article> findIsDelete();

}
