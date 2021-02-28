package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Wallpaper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
public interface WallpaperService extends IService<Wallpaper> {

    /**
     * 查询所有壁纸
     *
     * @return
     */
    public List<Wallpaper> findAllWallpaper();


    /**
     * 根据 壁纸标题 模糊查询
     *
     * @param title
     * @return
     */
    public List<Wallpaper> findWallpaperByTitle(String title);

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    public boolean likes(Long id);

    /**
     * 查询已删除的壁纸
     *
     * @return
     */
    public List<Wallpaper> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    public IPage<Wallpaper> pagingQuery(Integer index);

}
