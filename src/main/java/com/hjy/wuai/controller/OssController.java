package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.service.impl.OssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 10:03
 * @email: 541605007@qq.com
 * <p>
 * Oss控制器类
 */
@RestController
@RequestMapping("/oss")
@CrossOrigin(originPatterns = "*",allowCredentials = "true",allowedHeaders = "*",methods = {})
public class OssController {

    /**
     * 引入 ossService
     */
    @Autowired
    private OssServiceImpl ossService;


    /**
     * 壁纸上传到阿里oss服务器
     */
    @PostMapping("uploadPic")
    public Result<String> uploadPic(MultipartFile file) {
        String picUrl = ossService.uploadPic(file);
        if (StringUtils.isEmpty(picUrl)) {
            return Result.failed("上传失败");
        }
        System.out.println("success");
        return Result.ok(picUrl);
    }


    /**
     * 文章的封面上传到阿里oss服务器
     */
    @PostMapping("uploadArticle")
    public Result<String> uploadArticle(MultipartFile file) {
        String articleUrl = ossService.uploadArticle(file);
        if (StringUtils.isEmpty(articleUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(articleUrl);
    }

    /**
     * 游戏的封面上传到阿里oss服务器
     */
    @PostMapping("uploadGameCover")
    public Result<String> uploadGameCover(MultipartFile file) {
        String gameCoverUrl = ossService.uploadGameCover(file);
        if (StringUtils.isEmpty(gameCoverUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(gameCoverUrl);
    }


    /**
     * 音乐封面上传到阿里oss服务器
     */
    @PostMapping("uploadMusicCover")
    public Result<String> uploadMusicCover(MultipartFile file) {
        String musicCoverUrl = ossService.uploadMusicCover(file);
        if (StringUtils.isEmpty(musicCoverUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(musicCoverUrl);
    }


    /**
     * 音乐上传到阿里oss服务器
     */
    @PostMapping("uploadMusic")
    public Result<String> uploadMusic(MultipartFile files) {
        String musicUrl = ossService.uploadMusic(files);
        if (StringUtils.isEmpty(musicUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(musicUrl);
    }


    /**
     * 视频上传到阿里oss服务器
     */
    @PostMapping("uploadVideo")
    public Result<String> uploadVideo(MultipartFile file) {
        String videoUrl = ossService.uploadVideo(file);
        if (StringUtils.isEmpty(videoUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(videoUrl);
    }


    @GetMapping("delete}")
    public Result1 delete(String filepath) {
        boolean b = ossService.deleteFile(filepath);
        if (b) {
            return Result1.success().setMessage("删除成功").data("status", b);
        } else {
            return Result1.fail().setMessage("删除失败").data("status", b);
        }
    }
}
