package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.service.impl.MusicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicServiceImpl musicService;


    @PostMapping("/save")
    public String save(Music entity) {
        return musicService.save(entity) == true ? "success" : "fail";
    }

    @PostMapping("upload")
    public String upload(Music music) {
        return musicService.save(music) == true ? "admin" : "fail";
    }

    @GetMapping("getById")
    public String getById(Long id) {
        Music music = musicService.getById(id);
        return "admin";
    }

    @PostMapping("updateById")
    public String updateById(Music music) {
        return musicService.updateById(music) == true ? "admin" : "fail";
    }

    @GetMapping("removeById")
    public String removeById(Long id) {
        return musicService.removeById(id) == true ? "admin" : "fail";
    }

    @GetMapping("/findAllMusic")
    public String findAllMusic() {
        List<Music> musicList = musicService.findAllMusic();
        return "admin";
    }

    @GetMapping("findMusicByTitle")
    public String findMusicByTitle(String song) {
        List<Music> Music = musicService.findMusicByTitle(song);
        return "admin";
    }

    @GetMapping("likes")
    public String likes(Long id) {
        return musicService.likes(id) == true ? "admin" : "fail";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<Music> musicList = musicService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable musicIPage = musicService.pagingQuery(index);
        System.out.println(musicIPage);
        return "admin";
    }

}

