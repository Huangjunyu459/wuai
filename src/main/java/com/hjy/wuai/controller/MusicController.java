package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.MusicServiceImpl;
import com.hjy.wuai.service.impl.UserServiceImpl;
import com.hjy.wuai.service.impl.ValidateServiceImpl;
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
@RequestMapping("/music")
@CrossOrigin(originPatterns = "*",allowCredentials = "true",allowedHeaders = "*",methods = {})
public class MusicController {

    /**
     * 注入 musicService
     */
    @Autowired
    private MusicServiceImpl musicService;

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
     * 上传音频
     *
     * @param music 音频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addMusic")
    public Result1 addMusic(@RequestBody Music music) {
        if (musicService.save(music)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取音频（已过审）
     *
     * @param id 音频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Music music = musicService.getById(id);
        if (music != null) {
            return Result1.success().data("music", music);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 根据 id 获取音频（未过审）
     *
     * @param id 音频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Music music = musicService.getByIdNoExamine(id);
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
    @PostMapping("updateMusicById")
    public Result1 updateMusicById(@RequestBody Music music) {
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
    @DeleteMapping("removeMusicById")
    public Result1 removeMusicById(String id, String authorId) {
        User user = userService.getById(authorId);
        if (musicService.removeById(id)&& validateService.sendEmailNoExamine(user.getEmail())) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查找所有已审核的音频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllMusicExamine")
    public Result1 findAllMusicExamine() {
        List<Music> musicList = musicService.findAllMusicExamine();
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }


    /**
     * 查找所有未审核的音频
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllMusicNoExamine")
    public Result1 findAllMusicNoExamine() {
        List<Music> musicList = musicService.findAllMusicNoExamine();
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 查找最新的十六首歌曲
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findSixthMusic")
    public Result1 findSixthMusic() {
        List<Music> musicList = musicService.findSixthMusic();
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
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
        if (musicService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 歌名 模糊查询（已过审）
     *
     * @param song 音频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findMusicBySongExamine")
    public Result1 findMusicBySongExamine(String song) {
        List<Music> musicList = musicService.findMusicBySongExamine(song);
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }


    /**
     * 根据 歌名 模糊查询（未过审）
     *
     * @param song 音频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findMusicBySongNoExamine")
    public Result1 findMusicBySongNoExamine(String song) {
        List<Music> musicList = musicService.findMusicBySongNoExamine(song);
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("音频不存在");
        }
    }

    /**
     * 根据 歌名 模糊查询（已删除）
     *
     * @param song 音频标题
     * @return 返回的结果 msg
     */
    @GetMapping("findMusicBySongIsDelete")
    public Result1 findMusicBySongIsDelete(String song) {
        List<Music> musicList = musicService.findMusicBySongIsDelete(song);
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
    public Result1 likes(String id) {
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
     * 壁纸的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String song, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable musicIPage = musicService.pagingQueryExamine(song, index, size);
        if (musicIPage != null) {
            return Result1.success().data("musicIPage", musicIPage);
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
    public Result1 pagingQueryNoExamine(String song, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable musicIPage = musicService.pagingQueryNoExamine(song, index, size);
        if (musicIPage != null) {
            return Result1.success().data("musicIPage", musicIPage);
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
    public Result1 pagingQueryIsDelete(String song, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Music> musicList = musicService.pagingQueryIsDelete(song, index, size);
        if (musicList.size() != 0) {
            return Result1.success().data("musicList", musicList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据 音频 id 查询所属分类
     *
     * @param mid 音频的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByWid")
    public Result1 findCategoryNameByMid(String mid) {
        String categoryName = musicService.findCategoryNameByMid(mid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }
}

