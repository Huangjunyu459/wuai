package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Video;
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
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 查询已删除的视频
     *
     * @return 返回已被删除的视频集合
     */
    @Select("select * from video where is_delete = 1")
    List<Video> findIsDelete();

    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param vid 视频 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN video " +
            "ON video.category_id = category.id " +
            "WHERE video.id = #{wid}")
    String findCategoryNameByVid(Long vid);

}
