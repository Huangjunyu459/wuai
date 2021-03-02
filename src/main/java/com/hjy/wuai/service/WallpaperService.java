package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * 查询所有为审核的壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findAllWallpaperNoExamine();


    /**
     * 审核功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    boolean examine(Long id);


    /**
     * 根据 壁纸标题 模糊查询
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    List<Wallpaper> findWallpaperByTitle(String title);


    /**
     * 点赞功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    boolean likes(Long id);


    /**
     * 查询已删除的壁纸
     *
     * @return 返回的结果
     */
    List<Wallpaper> findIsDelete();

    /**
     * 分页查询
     *
     * @param index 索引号
     * @return 返回的结果
     */
    IPage<Wallpaper> pagingQuery(Integer index);


    /**
     * 根据 壁纸的 id 查询 所属的分类名
     *
     * @param wid 壁纸 id
     * @return 分类名称
     */
    String findCategoryNameByWid(Long wid);

}
