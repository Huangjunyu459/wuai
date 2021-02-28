package com.hjy.wuai.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.hjy.wuai.pojo.Music;
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
import java.util.Objects;
import java.util.UUID;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 9:54
 * @email: 541605007@qq.com
 * Oss服务接口实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OssServiceImpl implements OssService {


    @Autowired
    private OSSClient ossClient;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private MusicServiceImpl musicService;

    @Autowired
    private WallpaperServiceImpl wallpaperService;

    /**
     * 上传图片到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String upload(MultipartFile file) {
        String url = "";
        try {
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(OssConstant.END_POINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            System.out.println("上传之前");
            ossClient.putObject(OssConstant.BUCKET, fileName, inputStream);
            System.out.println("上传后");
            url = "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + fileName;
            if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".mp3")) {
                Music music = new Music();
                music.setSinger(file.getOriginalFilename().split("-")[0]);
                music.setSong(file.getOriginalFilename().split("-")[1]);
                music.setOssSong(fileName);
                music.setOosSrc(url);
                music.setAuthorId(1365502991892807681L);
                music.setCategoryId(4);
                try {
                    musicService.save(music);
                } catch (Exception e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    e.printStackTrace();
                }
            } else {
                Wallpaper wallpaper = new Wallpaper();
                wallpaper.setCategoryId(1);
                wallpaper.setAuthorId(1365502991892807681L);
                wallpaper.setOosSrc(url);
                wallpaper.setTitle(file.getOriginalFilename());
                wallpaper.setOosTitle(fileName);
                try {
                    wallpaperService.save(wallpaper);
                } catch (Exception e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
            return url;
        }
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
