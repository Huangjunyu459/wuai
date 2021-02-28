package com.hjy.wuai.service;

import org.springframework.web.multipart.MultipartFile;

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
     * @param file
     * @return oss服务器图片访问url
     */
    public String upload(MultipartFile file);


    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath);


}
