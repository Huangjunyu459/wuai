package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Wallpaper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
public interface WallpaperService extends IService<Wallpaper> {

    /**
     * 查询所有已审核的壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findAllWallpaperExamine();


    /**
     * 查询所有未审核的壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findAllWallpaperNoExamine();

    /**
     * 查询已删除的壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findIsDelete();

    /**
     * 查询前八张壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findEightWallpaper();

    /**
     * 查询最热门的前五张壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findFiveHotWallpaper();


    /**
     * 根据 壁纸标题 模糊查询（已过审）
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    List<Wallpaper> findWallpaperByTitleExamine(String title);


    /**
     * 根据 壁纸标题 模糊查询（未过审）
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    List<Wallpaper> findWallpaperByTitleNoExamine(String title);


    /**
     * 根据 壁纸标题 模糊查询（已删除）
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    List<Wallpaper> findWallpaperByTitleByIsDelete(String title);

    /**
     * 审核功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    boolean examine(String id);


    /**
     * 点赞功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    boolean likes(String id);


    /**
     * 分页查询（过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Wallpaper> pagingQueryExamine(String title, Integer index, Integer size);

    /**
     * 分页查询（未过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Wallpaper> pagingQueryNoExamine(String title, Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Wallpaper> pagingQueryIsDelete(String title, Integer index, Integer size);


    /**
     * 根据 壁纸的 id 查询 所属的分类名
     *
     * @param wid 壁纸 id
     * @return 分类名称
     */
    String findCategoryNameByWid(String wid);

}
