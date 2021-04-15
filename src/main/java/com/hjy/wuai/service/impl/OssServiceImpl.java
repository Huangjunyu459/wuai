package com.hjy.wuai.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.OssService;
import com.hjy.wuai.utils.OssConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author hjy
 * @date 2021/2/28 0028,上午 9:54
 * @email 541605007@qq.com
 * Oss服务接口实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OssServiceImpl implements OssService {

    /**
     * 注入 ossClient
     */
    @Autowired
    private OSSClient ossClient;


    /**
     * 注入 articleService
     */
    @Autowired
    private ArticleServiceImpl articleService;

    /**
     * 注入 videoService
     */
    @Autowired
    private VideoServiceImpl videoService;


    /**
     * 注入 musicService
     */
    @Autowired
    private MusicServiceImpl musicService;


    /**
     * 注入 wallpaperService
     */
    @Autowired
    private WallpaperServiceImpl wallpaperService;


    /**
     * 上传图片到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadPic(MultipartFile file) {
        String url = "";
        try {
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "pic/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }


    /**
     * 上传图片到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadArticle(MultipartFile file) {
        String url = "";
        try {
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "article/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    /**
     * 上传游戏的封面图到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadGameCover(MultipartFile file) {
        String url = "";
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "game/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    /**
     * 上传音频的封面图到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadMusicCover(MultipartFile file) {
        String url = "";
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "music/pic/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }


    /**
     * 上传音频到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadMusic(MultipartFile file) {
        String url = "";
        try {
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "music/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }


    /**
     * 上传视频到OSS服务器
     *
     * @return oss服务器视频访问url
     */
    @Override
    public String uploadVideo(MultipartFile file) {
        String url = "";
        try {
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            String type = "video/" + fileName;
            ossClient.putObject(OssConstant.BUCKET, type, inputStream);
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + type;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }


    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    @Override
    public boolean deleteFile(String filePath) {
        OSSClient ossClient = new OSSClient(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
        boolean exist = ossClient.doesObjectExist(OssConstant.BUCKET, filePath);
        if (!exist) {
            log.error("文件不存在,filePath={}", filePath);
            return false;
        }
        log.info("删除文件,filePath={}", filePath);
        ossClient.deleteObject(OssConstant.BUCKET, filePath);
        ossClient.shutdown();
        return true;
    }

}
