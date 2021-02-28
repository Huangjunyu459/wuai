package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.WallpaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {

    @Autowired
    private WallpaperServiceImpl wallpaperService;

    @PostMapping("/save")
    public String save(Wallpaper entity) {
        return wallpaperService.save(entity) == true ? "success" : "fail";
    }

}

