package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Repository
public interface GameMapper extends BaseMapper<Game> {

    /**
     * 查询已删除的作品
     *
     * @return 返回 Game 的集合
     */
    @Select("select * from game where is_delete = 1")
    List<Game> findIsDelete();

    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param gid 视频 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN game " +
            "ON game.category_id = category.id " +
            "WHERE game.id = #{gid}")
    String findCategoryNameByGid(Long gid);

}
