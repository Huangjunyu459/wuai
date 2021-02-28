package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result;
import com.hjy.wuai.service.impl.OssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("upload")
    public Result<String> upload() {
        String picUrl = ossService.uploadPic();
        if (StringUtils.isEmpty(picUrl)) {
            return Result.failed("上传失败");
        }
        return Result.ok(picUrl);
    }
}
