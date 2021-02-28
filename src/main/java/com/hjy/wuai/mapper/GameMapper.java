package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Repository
public interface GameMapper extends BaseMapper<Game> {

    /**
     * 查询已删除的作品
     * @return 返回 Game 的集合
     */
    @Select("select * from game where is_delete = 1")
     List<Game> findIsDelete();

    /**
     * 查询所有的游戏
     * @return 返回游戏的集合
     */
    @Select("select * from game where is_delete = 0")
    List<Game> findAll();

}
