package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.UserServiceImpl;
import com.hjy.wuai.service.impl.ValidateServiceImpl;
import com.hjy.wuai.service.impl.WallpaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(originPatterns = "*",allowCredentials = "true",allowedHeaders = "*",methods = {})
public class WallpaperController {

    /**
     * 注入 wallpaperService
     */
    @Autowired
    private WallpaperServiceImpl wallpaperService;

    /**
     * 注入 validateService
     */
    @Autowired
    private ValidateServiceImpl validateService;

    /**
     * 注入 userService
     */
    @Autowired
    private UserServiceImpl userService;


    /**
     * 上传壁纸
     *
     * @param wallpaper 壁纸实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addWallpaper")
    public Result1 addWallpaper(@RequestBody Wallpaper wallpaper) {
        if (wallpaperService.save(wallpaper)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取壁纸（已过审）
     *
     * @param id 壁纸 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Wallpaper wallpaper = wallpaperService.getById(id);
        if (wallpaper != null) {
            return Result1.success().data("wallpaper", wallpaper);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 根据 id 获取壁纸（未过审）
     *
     * @param id 壁纸 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Wallpaper wallpaper = wallpaperService.getByIdNoExamine(id);
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
    @PostMapping("updateWallpaperById")
    public Result1 updateWallpaperById(@RequestBody Wallpaper wallpaper) {
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
    @DeleteMapping("removeWallpaperById")
    public Result1 removeWallpaperById(String id, String authorId) {
        User user = userService.getById(authorId);
        if (wallpaperService.removeById(id) && validateService.sendEmailNoExamine(user.getEmail())) {
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
    @GetMapping("findAllWallpaperExamine")
    public Result1 findAllWallpaperExamine() {
        List<Wallpaper> wallpaperList = wallpaperService.findAllWallpaperExamine();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }


    /**
     * 查找所有未审核的壁纸
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findAllWallpaperNoExamine")
    public Result1 findAllWallpaperNoExamine() {
        List<Wallpaper> wallpaperList = wallpaperService.findAllWallpaperNoExamine();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 查询前八张壁纸
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findEightWallpaper")
    public Result1 findEightWallpaper() {
        List<Wallpaper> wallpaperList = wallpaperService.findEightWallpaper();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    @GetMapping("findFiveHotWallpaper")
    public Result1 findFiveHotWallpaper() {
        List<Wallpaper> wallpaperList = wallpaperService.findFiveHotWallpaper();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }


    /**
     * 根据 壁纸标题查询（已过审）
     *
     * @param title 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findWallpaperByTitleExamine")
    public Result1 findWallpaperByTitleExamine(String title) {
        List<Wallpaper> wallpaperList = wallpaperService.findWallpaperByTitleExamine(title);
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 根据 壁纸标题查询（未过审）
     *
     * @param title 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findWallpaperByTitleNoExamine")
    public Result1 findWallpaperByTitleNoExamine(String title) {
        List<Wallpaper> wallpaperList = wallpaperService.findWallpaperByTitleNoExamine(title);
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("壁纸不存在");
        }
    }

    /**
     * 根据 壁纸标题查询（已删除）
     *
     * @param title 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findWallpaperByTitleByIsDelete")
    public Result1 findWallpaperByTitleByIsDelete(String title) {
        List<Wallpaper> wallpaperList = wallpaperService.findWallpaperByTitleByIsDelete(title);
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
    public Result1 likes(String id) {
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
     * 审核功能
     *
     * @param id 壁纸的 id
     * @return 返回结果 msg
     */
    @GetMapping("examine")
    public Result1 examine(String id) {
        if (wallpaperService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 壁纸的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable wallpaperIPage = wallpaperService.pagingQueryExamine(title, index, size);
        if (wallpaperIPage != null) {
            return Result1.success().data("wallpaperIPage", wallpaperIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 壁纸的分页查询（未过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryNoExamine")
    public Result1 pagingQueryNoExamine(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable wallpaperIPage = wallpaperService.pagingQueryNoExamine(title, index, size);
        if (wallpaperIPage != null) {
            return Result1.success().data("wallpaperIPage", wallpaperIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 壁纸的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String title, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Wallpaper> wallpaperList = wallpaperService.pagingQueryIsDelete(title, index, size);
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据 壁纸 id 查询所属分类
     *
     * @param wid 壁纸的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByWid")
    public Result1 findCategoryNameByWid(String wid) {
        String categoryName = wallpaperService.findCategoryNameByWid(wid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

