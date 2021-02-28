package com.hjy.wuai.service.impl;

import com.aliyun.oss.OSSClient;
import com.hjy.wuai.service.OssService;
import com.hjy.wuai.utils.OssConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,上午 9:54
 * @email: 541605007@qq.com
 * Oss服务接口实现类
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {


    @Autowired
    private OSSClient ossClient;

    /**
     * 上传图片到OSS服务器
     *
     * @return oss服务器图片访问url
     */
    @Override
    public String uploadPic() {
        String picUrl = "";
        try {
            //1.从本地读取图片文件
            File file = new File("C:\\Users\\Administrator\\Desktop\\1.jpg");
            MultipartFile multipartFile = new MockMultipartFile(file.getName(),
                    file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(),
                    new FileInputStream(file));
            //2.生成oss服务器图片名称（这里用uuid拼接一个name，防止上传图片与其他图片重名而覆盖同名oss文件）
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename();
            //3.上传到oss服务器
            picUrl = uploadToOss(fileName, new ByteArrayInputStream(multipartFile.getBytes()));
        } catch (Exception e) {
            log.error("picture upload failed. error:", e);
        }
        return picUrl;
    }

    /**
     * 上传图片
     *
     * @param fileName 图片名称，图片名称包括文件夹名称和“/”
     * @param in       图片输入流
     */
    private String uploadToOss(String fileName, InputStream in) {
        // 上传Object.
        ossClient.putObject(OssConstant.BUCKET, fileName, in);
        //返回oss服务器访问上传图片的url
        return "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + fileName;
    }

}
