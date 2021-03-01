package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Result1;
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

    /**
     * 上传壁纸
     *
     * @param wallpaper 壁纸实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("/save")
    public Result1 save(Wallpaper wallpaper) {
        if (wallpaperService.save(wallpaper)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取壁纸
     *
     * @param id 壁纸 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(Long id) {
        Wallpaper wallpaper = wallpaperService.getById(id);
        if (wallpaper != null) {
            return Result1.success().data("wallpaper", wallpaper);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 更新壁纸信息
     *
     * @param wallpaper 壁纸实体
     * @return 返回的结果 msg
     */
    @PostMapping("updateById")
    public Result1 updateById(Wallpaper wallpaper) {
        if (wallpaperService.updateById(wallpaper)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }

    /**
     * 根据 id 删除壁纸
     *
     * @param id 壁纸 id
     * @return 返回的结果 msg
     */
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
        if (wallpaperService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }

    /**
     * 查找所有壁纸
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllWallpaper")
    public Result1 findAllWallpaper() {
        List<Wallpaper> wallpaperList = wallpaperService.findAllWallpaper();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 根据 壁纸标题查询
     *
     * @param title 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findWallpaperByTitle")
    public Result1 findWallpaperByTitle(String title) {
        List<Wallpaper> wallpaperList = wallpaperService.findWallpaperByTitle(title);
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 壁纸点赞
     *
     * @param id 壁纸的 id
     * @return 返回的结果 msg
     */
    @GetMapping("likes")
    public Result1 likes(Long id) {
        if (wallpaperService.likes(id)) {
            return Result1.success().setMessage("点赞成功");
        } else {
            return Result1.fail().setMessage("点赞失败");
        }
    }

    /**
     * 查询已删除的壁纸
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Wallpaper> wallpaperList = wallpaperService.findIsDelete();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 壁纸分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable wallpaperIPage = wallpaperService.pagingQuery(index);
        if (wallpaperIPage != null) {
            return Result1.success().data("wallpaperIPage", wallpaperIPage);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 根据 壁纸 id 查询所属分类
     *
     * @param wid 壁纸的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByWid")
    public Result1 findCategoryNameByWid(Long wid) {
        String categoryName = wallpaperService.findCategoryNameByWid(wid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }

}

