package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.VideoServiceImpl;
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
@RequestMapping("/video")
@CrossOrigin
public class VideoController {

    /**
     * 注入 videoService
     */
    @Autowired
    private VideoServiceImpl videoService;


    /**
     * 上传视频
     *
     * @param video 视频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addVideo")
    public Result1 addVideo(@RequestBody Video video) {
        if (videoService.save(video)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取视频（已过审）
     *
     * @param id 视频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Video video = videoService.getById(id);
        if (video != null) {
            return Result1.success().data("video", video);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }

    /**
     * 根据 id 获取视频（未过审）
     *
     * @param id 视频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Video video = videoService.getByIdNoExamine(id);
        if (video != null) {
            return Result1.success().data("video", video);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 更新视频信息
     *
     * @param video 视频实体
     * @return 返回的结果 msg
     */
    @PostMapping("updateVideoById")
    public Result1 updateVideoById(@RequestBody Video video) {
        if (videoService.updateById(video)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }


    /**
     * 根据 id 删除视频
     *
     * @param id 视频 id
     * @return 返回的结果 msg
     */
    @DeleteMapping("removeVideoById")
    public Result1 removeVideoById(String id) {
        if (videoService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查找所有已审核的视频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllVideoExamine")
    public Result1 findAllVideoExamine() {
        List<Video> videoList = videoService.findAllVideoExamine();
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 查找所有未审核的视频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllVideoNoExamine")
    public Result1 findAllVideoNoExamine() {
        List<Video> videoList = videoService.findAllVideoNoExamine();
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 审核功能
     *
     * @param id 视频的 id
     * @return 返回结果 msg
     */
    @GetMapping("examine")
    public Result1 examine(String id) {
        if (videoService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 视频名称 查询（已过审）
     *
     * @param videoName 视频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findVideoByVideoNameExamine")
    public Result1 findVideoByVideoNameExamine(String videoName) {
        List<Video> videoList = videoService.findVideoByVideoNameExamine(videoName);
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }

    /**
     * 根据 视频名称 查询（未过审）
     *
     * @param videoName 视频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findVideoByVideoNameNoExamine")
    public Result1 findVideoByVideoNameNoExamine(String videoName) {
        List<Video> videoList = videoService.findVideoByVideoNameNoExamine(videoName);
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }

    /**
     * 根据 视频名称 查询（已删除）
     *
     * @param videoName 视频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findVideoByVideoNameIsDelete")
    public Result1 findVideoByVideoNameIsDelete(String videoName) {
        List<Video> videoList = videoService.findVideoByVideoNameIsDelete(videoName);
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 视频点赞
     *
     * @param id 视频的 id
     * @return 返回的结果 msg
     */
    @GetMapping("likes")
    public Result1 likes(String id) {
        if (videoService.likes(id)) {
            return Result1.success().setMessage("点赞成功");
        } else {
            return Result1.fail().setMessage("点赞失败");
        }
    }


    /**
     * 查询已删除的视频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Video> videoList = videoService.findIsDelete();
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 壁纸的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String videoName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable videoIPage = videoService.pagingQueryExamine(videoName,index, size);
        if (videoIPage != null) {
            return Result1.success().data("videoIPage", videoIPage);
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
    public Result1 pagingQueryNoExamine(String videoName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable videoIPage = videoService.pagingQueryNoExamine(videoName,index, size);
        if (videoIPage != null) {
            return Result1.success().data("videoIPage", videoIPage);
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
    public Result1 pagingQueryIsDelete(String videoName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Video> videoList = videoService.pagingQueryIsDelete(videoName,index, size);
        if (videoList.size() != 0) {
            return Result1.success().data("videoList", videoList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param vid 视频 id
     * @return 分类的名称
     */
    @GetMapping("findCategoryNameByVid")
    public Result1 findCategoryNameByVid(String vid) {
        String categoryName = videoService.findCategoryNameByVid(vid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

