package com.hjy.wuai.service;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 9:53
 * @email: 541605007@qq.com
 * Oss服务接口类
 */
public interface OssService {

    /**
     * 上传图片到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    String uploadPic();
}
