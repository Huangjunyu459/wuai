package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Wallpaper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@Repository
public interface WallpaperMapper extends BaseMapper<Wallpaper> {

    /**
     * 查询已删除的壁纸
     *
     * @return 返回已被删除的壁纸集合
     */
    @Select("select * from wallpaper where is_delete = 1")
    List<Wallpaper> findIsDelete();


    /**
     * 根据 壁纸的 id 查询 所属的分类名
     *
     * @param wid 壁纸 id
     * @return 分类名称
     */
    @Select("SELECT category.category_name FROM category " +
            "INNER JOIN wallpaper " +
            "ON wallpaper.category_id = category.id " +
            "WHERE wallpaper.examine = 1 AND  wallpaper.id = #{wid}")
    String findCategoryNameByWid(Long wid);

}
