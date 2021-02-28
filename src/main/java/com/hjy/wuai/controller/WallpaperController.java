package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.WallpaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

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

    @PostMapping("upload")
    public String upload(Wallpaper wallpaper) {
        return wallpaperService.save(wallpaper) == true ? "admin" : "fail";
    }

    @GetMapping("getById")
    public String getById(Long id) {
        Wallpaper wallpaper = wallpaperService.getById(id);
        return "admin";
    }

    @PostMapping("updateById")
    public String updateById(Wallpaper wallpaper) {
        return wallpaperService.updateById(wallpaper) == true ? "admin" : "fail";
    }

    @GetMapping("removeById")
    public String removeById(Long id) {
        return wallpaperService.removeById(id) == true ? "admin" : "fail";
    }

    @GetMapping("/findAllWallpaper")
    public String findAllWallpaper() {
        List<Wallpaper> wallpaperList = wallpaperService.findAllWallpaper();
        return "admin";
    }

    @GetMapping("findWallpaperByTitle")
    public String findWallpaperByTitle(String title) {
        List<Wallpaper> wallpaperList = wallpaperService.findWallpaperByTitle(title);
        return "admin";
    }

    @GetMapping("likes")
    public String likes(Long id) {
        return wallpaperService.likes(id) == true ? "admin" : "fail";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<Wallpaper> wallpaperList = wallpaperService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable wallpaperIPage = wallpaperService.pagingQuery(index);
        System.out.println(wallpaperIPage);
        return "admin";
    }

}

