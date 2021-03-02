package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.service.impl.VideoServiceImpl;
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
@RequestMapping("/video")
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
    @PostMapping("save")
    public Result1 save(Video video) {
        if (videoService.save(video)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取视频
     *
     * @param id 视频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(Long id) {
        Video video = videoService.getById(id);
        if (video != null) {
            return Result1.success().data("wallpaper", video);
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
    @PostMapping("updateById")
    public Result1 updateById(Video video) {
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
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
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
    public Result1 examine(Long id) {
        if (videoService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 视频名称 查询
     *
     * @param videoName 视频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findVideoByVideoName")
    public Result1 findVideoByVideoName(String videoName) {
        List<Video> videoList = videoService.findVideoByVideoName(videoName);
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
    public Result1 likes(Long id) {
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
     * 视频分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable videoIPage = videoService.pagingQuery(index);
        if (videoIPage != null) {
            return Result1.success().data("videoIPage", videoIPage);
        } else {
            return Result1.fail().setMessage("视频不存在");
        }
    }


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param vid 视频 id
     * @return 分类的名称
     */
    @GetMapping("findCategoryNameByVid")
    public Result1 findCategoryNameByVid(Long vid) {
        String categoryName = videoService.findCategoryNameByVid(vid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

