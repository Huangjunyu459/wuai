package com.hjy.wuai.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hjy
 * @date 2021/2/28 0028,上午 9:53
 * @email 541605007@qq.com
 * Oss服务接口类
 */
public interface OssService {

    /**
     * 上传图片到OSS服务器
     *
     * @param file
     * @return oss服务器图片访问url
     */
    String uploadPic(MultipartFile file);


    /**
     * 上传图片到OSS服务器
     *
     * @param file
     * @return oss服务文章封面访问url
     */
    String uploadArticle(MultipartFile file);


    /**
     * 上传音频到OSS服务器
     *
     * @param file
     * @return oss服务器音频访问url
     */
    String uploadMusic(MultipartFile file);

    /**
     * 上传视频到OSS服务器
     *
     * @param file
     * @return oss服务器视频访问url
     */
    String uploadVideo(MultipartFile file);


    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    boolean deleteFile(String filePath);


}
