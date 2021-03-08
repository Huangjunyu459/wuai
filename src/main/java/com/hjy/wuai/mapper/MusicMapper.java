package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.pojo.Video;
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
public interface MusicMapper extends BaseMapper<Music> {


    /**
     * 查询已删除的音乐
     *
     * @return 返回已被删除的音乐集合
     */
    @Select("select * from music where is_delete = 1")
    List<Music> findIsDelete();


    /**
     * 根据名字模糊查找已删除的音频
     *
     * @param song 壁纸标题
     * @return 返回的结果
     */
    @Select("SELECT * FROM music WHERE is_delete = 1 AND song LIKE #{song} ")
    List<Music> findMusicBySongIsDelete(String song);


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param mid 视频 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN music " +
            "ON music.category_id = category.id " +
            "WHERE music.examine = 1 AND music.id = #{wid}")
    String findCategoryNameByMid(String mid);

    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param song
     * @return
     */
    @Select("select * from music where is_delete = 1 AND song like #{song} limit #{index} , #{size}")
    List<Music> pagingQueryIsDelete(String song,Integer index, Integer size);

}
