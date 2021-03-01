package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.pojo.Result1;
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
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    /**
     * 注入 musicService
     */
    @Autowired
    private MusicServiceImpl musicService;


    /**
     * 上传音频
     *
     * @param music 音频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("/save")
    public Result1 save(Music music) {
        if (musicService.save(music)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取音频
     *
     * @param id 音频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(Long id) {
        Music music = musicService.getById(id);
        if (music != null) {
            return Result1.success().data("music", music);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 更新音频信息
     *
     * @param music 音频实体
     * @return 返回的结果 msg
     */
    @PostMapping("updateById")
    public Result1 updateById(Music music) {
        if (musicService.updateById(music)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }

    /**
     * 根据 id 删除音频
     *
     * @param id 音频 id
     * @return 返回的结果 msg
     */
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
        if (musicService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }

    /**
     * 查找所有音频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllMusic")
    public Result1 findAllMusic() {
        List<Music> wallpaperList = musicService.findAllMusic();
        if (wallpaperList.size() != 0) {
            return Result1.success().data("wallpaperList", wallpaperList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 根据 歌名 模糊查询
     *
     * @param song 音频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findMusicBySong")
    public Result1 findMusicBySong(String song) {
        List<Music> musicList = musicService.findMusicBySong(song);
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 音频点赞
     *
     * @param id 音频的 id
     * @return 返回的结果 msg
     */
    @GetMapping("likes")
    public Result1 likes(Long id) {
        if (musicService.likes(id)) {
            return Result1.success().setMessage("点赞成功");
        } else {
            return Result1.fail().setMessage("点赞失败");
        }
    }

    /**
     * 查询已删除的音频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Music> musicList = musicService.findIsDelete();
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 音频分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable musicIPage = musicService.pagingQuery(index);
        if (musicIPage != null) {
            return Result1.success().data("musicIPage", musicIPage);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 根据 音频 id 查询所属分类
     *
     * @param mid 音频的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByWid")
    public Result1 findCategoryNameByMid(Long mid) {
        String categoryName = musicService.findCategoryNameByMid(mid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }

    }
}

