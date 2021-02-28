package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
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

    @Autowired
    private VideoServiceImpl videoService;

    @PostMapping("upload")
    public String upload(Video video) {
        return videoService.save(video) == true ? "admin" : "fail";
    }

    @GetMapping("getById")
    public String getById(Long id) {
        Video video = videoService.getById(id);
        return "admin";
    }

    @PostMapping("updateById")
    public String updateById(Video video) {
        return videoService.updateById(video) == true ? "admin" : "fail";
    }

    @GetMapping("removeById")
    public String removeById(Long id) {
        return videoService.removeById(id) == true ? "admin" : "fail";
    }

    @GetMapping("/findAllVideo")
    public String findAllVideo() {
        List<Video> videos = videoService.findAllVideo();
        return "admin";
    }

    @GetMapping("findVideoByVideoName")
    public String findVideoByVideoName(String videoName) {
        List<Video> videos = videoService.findVideoByVideoName(videoName);
        return "admin";
    }

    @GetMapping("likes")
    public String likes(Long id) {
        return videoService.likes(id) == true ? "admin" : "fail";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<Video> isDelete = videoService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable videoIPage = videoService.pagingQuery(index);
        System.out.println(videoIPage);
        return "admin";
    }

}

