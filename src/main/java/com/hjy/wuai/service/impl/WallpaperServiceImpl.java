package com.hjy.wuai.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.WallpaperMapper;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean save(Wallpaper entity) {
        return wallpaperMapper.insert(entity) == 1 ? true : false;
    }
}

