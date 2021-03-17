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
     * @param file 图片文件
     * @return oss服务器图片访问url
     */
    String uploadPic(MultipartFile file);


    /**
     * 上传作品到OSS服务器
     *
     * @param file 作品文件
     * @return oss服务文章封面访问url
     */
    String uploadArticle(MultipartFile file);

    /**
     * 上传游戏封面图到OSS服务器
     *
     * @param file 作品文件
     * @return oss服务文章封面访问url
     */
    String uploadGameCover(MultipartFile file);


    /**
     * 上传音频的封面图到OSS服务器
     *
     * @param file 音频文件
     * @return oss服务器图片访问url
     */
     String uploadMusicCover(MultipartFile file);

    /**
     * 上传音频到OSS服务器
     *
     * @param file 音频文件
     * @return oss服务器音频访问url
     */
    String uploadMusic(MultipartFile file);


    /**
     * 上传视频到OSS服务器
     *
     * @param file 视频文件
     * @return oss服务器视频访问url
     */
    String uploadVideo(MultipartFile file);


    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 返回的结果
     */
    boolean deleteFile(String filePath);


}
