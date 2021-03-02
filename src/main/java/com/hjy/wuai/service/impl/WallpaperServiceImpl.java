package com.hjy.wuai.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.WallpaperMapper;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper> implements WallpaperService {

    @Autowired
    private WallpaperMapper wallpaperMapper;

    /**
     * 数据库插入壁纸记录
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Wallpaper entity) {
        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setTitle(entity.getTitle());
        wallpaper.setOssTitle(entity.getOssTitle());
        wallpaper.setOssSrc(entity.getOssSrc());
        wallpaper.setAuthorId(entity.getAuthorId());
        wallpaper.setCategoryId(entity.getCategoryId());
        return wallpaperMapper.insert(wallpaper) == 1 ? true : false;
    }


    /**
     * 根据 id 获取壁纸
     *
     * @param id
     * @return
     */
    @Override
    public Wallpaper getById(Serializable id) {
        return wallpaperMapper.selectById(id);
    }

    /**
     * 壁纸更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 壁纸实体
     * @return
     */
    @Override
    public boolean updateById(Wallpaper entity) {

        //  获取要更新的用户
        Wallpaper Wallpaper = getById(entity.getId());
        Wallpaper.setTitle(entity.getTitle());
        return wallpaperMapper.updateById(Wallpaper) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return wallpaperMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有作品
     *
     * @return
     */
    @Override
    public List<Wallpaper> findAllWallpaper() {
        return wallpaperMapper.selectList(null);
    }

    /**
     * 根据 壁纸标题 模糊查询
     *
     * @param title
     * @return
     */
    @Override
    public List<Wallpaper> findWallpaperByTitle(String title) {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        return wallpaperMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(Long id) {
        Wallpaper Wallpaper = wallpaperMapper.selectById(id);
        Wallpaper.setLove(Wallpaper.getLove() + 1);
        return wallpaperMapper.updateById(Wallpaper) == 1 ? true : false;
    }

    /**
     * 查询已删除的壁纸
     *
     * @return
     */
    @Override
    public List<Wallpaper> findIsDelete() {
        return wallpaperMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Wallpaper> pagingQuery(Integer index) {
        IPage<Wallpaper> page = new Page<>(index, 5);
        IPage<Wallpaper> userIPage = wallpaperMapper.selectPage(page, null);
        return userIPage;
    }

    /**
     * 根据 壁纸的 id 查询 所属的分类名
     *
     * @param wid 壁纸 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByWid(Long wid) {
        return wallpaperMapper.findCategoryNameByWid(wid);
    }
}

