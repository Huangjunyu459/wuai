package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
     * 根据名字模糊查找已删除的游戏
     *
     * @param gameName
     * @return 返回的结果
     */
    @Select("SELECT * FROM game WHERE is_delete = 1 AND game_name LIKE #{gameName} ")
    List<Game> findGameByGameNameIsDelete(String gameName);


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param gid 视频 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN game " +
            "ON game.category_id = category.id " +
            "WHERE game.examine = 1 AND game.id = #{gid}")
    String findCategoryNameByGid(String gid);

    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param gameName
     * @return
     */
    @Select("select * from game where is_delete = 1 AND game_name like #{gameName} limit #{index} , #{size}")
    List<Game> pagingQueryIsDelete(String gameName,Integer index, Integer size);

}
