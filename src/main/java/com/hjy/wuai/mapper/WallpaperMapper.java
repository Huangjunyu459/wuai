package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.User;
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
    String findCategoryNameByWid(String wid);

    /**
     * 根据名字模糊查找已删除的壁纸
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    @Select("SELECT * FROM wallpaper WHERE is_delete = 1 AND title LIKE #{title} ")
    List<Wallpaper> findWallpaperByTitleByIsDelete(String title);


    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param title
     * @return
     */
    @Select("select * from wallpaper where is_delete = 1 AND title like #{title}  limit #{index} , #{size}")
    List<Wallpaper> pagingQueryIsDelete(String title, Integer index, Integer size);

}
