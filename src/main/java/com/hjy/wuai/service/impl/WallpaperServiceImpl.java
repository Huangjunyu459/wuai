package com.hjy.wuai.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.WallpaperMapper;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * 注入 wallpaperMapper
     */
    @Autowired
    private WallpaperMapper wallpaperMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 数据库插入壁纸记录
     *
     * @param entity 壁纸实体类
     * @return 返回的结果
     */
    @Override
    public boolean save(Wallpaper entity) {
        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setTitle(entity.getTitle());
        wallpaper.setOssSrc(entity.getOssSrc());
        wallpaper.setAuthorId(entity.getAuthorId());
        wallpaper.setCategoryId(1);
        wallpaper.setIsVip(entity.getIsVip());
        return wallpaperMapper.insert(wallpaper) == 1;
    }


    /**
     * 根据 id 获取壁纸（已过审）
     *
     * @param id 壁纸的id
     * @return 返回的结果
     */
    @Override
    public Wallpaper getById(Serializable id) {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return wallpaperMapper.selectOne(wrapper);
    }

    /**
     * 根据 id 获取壁纸（未过审）
     *
     * @param id 壁纸的id
     * @return 返回的结果
     */
    public Wallpaper getByIdNoExamine(Serializable id) {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).eq("id", id);
        return wallpaperMapper.selectOne(wrapper);
    }


    /**
     * 壁纸更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 壁纸实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Wallpaper entity) {

        //  获取要更新的壁纸
        Wallpaper wallpaper = getById(entity.getId());
        wallpaper.setTitle(entity.getTitle());
        wallpaper.setOssSrc(entity.getOssSrc());
        wallpaper.setLove(entity.getLove());
        wallpaper.setIsVip(entity.getIsVip());
        return wallpaperMapper.updateById(wallpaper) == 1;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return wallpaperMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有已审核的壁纸
     *
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findAllWallpaperExamine() {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return wallpaperMapper.selectList(wrapper);
    }


    /**
     * 查询所有未审核的壁纸
     *
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findAllWallpaperNoExamine() {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return wallpaperMapper.selectList(wrapper);
    }


    /**
     * 审核功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(String id) {
        Wallpaper wallpaper = wallpaperMapper.selectById(id);
        wallpaper.setExamine(1);
        return wallpaperMapper.updateById(wallpaper) == 1;
    }


    /**
     * 根据 壁纸标题 模糊查询(已过审)
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findWallpaperByTitleExamine(String title) {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.like("title", title).eq("examine", 1);
        return wallpaperMapper.selectList(wrapper);
    }

    /**
     * 根据 壁纸标题 模糊查询(未过审)
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findWallpaperByTitleNoExamine(String title) {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.like("title", title).eq("examine", 0);
        return wallpaperMapper.selectList(wrapper);
    }

    /**
     * 根据 壁纸标题 模糊查询(已删除)
     *
     * @param title 壁纸标题
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findWallpaperByTitleByIsDelete(String title) {
        title = "%" + title + "%";
        return wallpaperMapper.findWallpaperByTitleByIsDelete(title);
    }


    /**
     * 点赞功能
     *
     * @param id 壁纸 id
     * @return 返回的结果
     */
    @Override
    public boolean likes(String id) {
        Wallpaper Wallpaper = wallpaperMapper.selectById(id);
        Wallpaper.setLove(Wallpaper.getLove() + 1);
        return wallpaperMapper.updateById(Wallpaper) == 1;
    }


    /**
     * 查询已删除的壁纸
     *
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findIsDelete() {
        return wallpaperMapper.findIsDelete();
    }


    /**
     * 查询前八张的壁纸
     *
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findEightWallpaper() {
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 8");
        return wallpaperMapper.selectList(wrapper);
    }

    /**
     * 查询最热门的五张壁纸
     *
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> findFiveHotWallpaper() {
        if (redisTemplate.hasKey("wallpaperList")) {
            System.out.println("在redis中查找");
            return (List<Wallpaper>) redisTemplate.opsForValue().get("wallpaperList");
        } else {
            QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("love").eq("examine", 1).last("limit 5");
            List<Wallpaper> wallpaperList = wallpaperMapper.selectList(wrapper);
            redisTemplate.opsForValue().set("wallpaperList", wallpaperList,30, TimeUnit.MINUTES);
            return wallpaperList;
        }
    }

    /**
     * 分页查询（过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Wallpaper> pagingQueryExamine(String title, Integer index, Integer size) {
        IPage<Wallpaper> page = new Page<>(index, size);
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).like("title", title);
        IPage<Wallpaper> wallpaperIPage = wallpaperMapper.selectPage(page, wrapper);
        return wallpaperIPage;
    }

    /**
     * 分页查询（未过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Wallpaper> pagingQueryNoExamine(String title, Integer index, Integer size) {
        IPage<Wallpaper> page = new Page<>(index, size);
        QueryWrapper<Wallpaper> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).like("title", title);
        IPage<Wallpaper> wallpaperIPage = wallpaperMapper.selectPage(page, wrapper);
        return wallpaperIPage;
    }

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<Wallpaper> pagingQueryIsDelete(String title, Integer index, Integer size) {
        title = "%" + title + "%";
        List<Wallpaper> wallpaperList = wallpaperMapper.pagingQueryIsDelete(title, index, size);
        return wallpaperList;
    }


    /**
     * 根据 壁纸的 id 查询 所属的分类名
     *
     * @param wid 壁纸 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByWid(String wid) {
        return wallpaperMapper.findCategoryNameByWid(wid);
    }

}

