package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result;
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
        return Result.ok(picUrl);
    }


    /**
     * 文章的封面上传到阿里oss服务器
     */
    @PostMapping("uploadArticle")
    public Result<String> uploadArticle(MultipartFile file) {
        String picUrl = ossService.uploadArticle(file);
        if (StringUtils.isEmpty(picUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(picUrl);
    }


    /**
     * 音乐上传到阿里oss服务器
     */
    @PostMapping("uploadMusic")
    public Result<String> uploadMusic(MultipartFile file) {
        String picUrl = ossService.uploadMusic(file);
        if (StringUtils.isEmpty(picUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(picUrl);
    }


    /**
     * 视频上传到阿里oss服务器
     */
    @PostMapping("uploadVideo")
    public Result<String> uploadVideo(MultipartFile file) {
        String picUrl = ossService.uploadVideo(file);
        if (StringUtils.isEmpty(picUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(picUrl);
    }


    @GetMapping("delete/{filepath}")
    public void delete(@PathVariable String filepath, Model model) {
        boolean b = ossService.deleteFile(filepath);
        if (b) {
            model.addAttribute("msg", "删除成功");
        } else {
            model.addAttribute("msg", "删除失败");
        }
    }
}
