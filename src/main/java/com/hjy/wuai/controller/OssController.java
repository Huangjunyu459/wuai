package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result;
import com.hjy.wuai.service.impl.OssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 10:03
 * @email: 541605007@qq.com
 * <p>
 * Oss控制器类
 */
@RestController
@RequestMapping("oss")
public class OssController {


    @Autowired
    private OssServiceImpl ossService;


    /**
     * 文件上传到阿里oss服务器
     */
    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) {
        String picUrl = ossService.upload(file);
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
