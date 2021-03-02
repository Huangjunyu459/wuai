package com.hjy;

import com.hjy.wuai.controller.VideoController;
import com.hjy.wuai.mapper.VideoMapper;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.VideoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,下午 22:44
 * @email: 541605007@qq.com
 */
@SpringBootTest
public class VideoTest {

    @Autowired
    private VideoController videoController;

    @Autowired
    private VideoServiceImpl videoService;

    @Autowired
    private VideoMapper videoMapper;

    @Test
    void testFindAll() {
        System.out.println(videoController.findAllVideoExamine());
        List<Video> wallpaperList = videoService.findAllVideoExamine();
        Iterator<Video> iterator = wallpaperList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    @Test
    void testPage() {

        videoController.pagingQuery(1);
    }

    @Test
    void save(){
        Video video = new Video();
        video.setVideoName("videoName");
        video.setOssName("fileName");
        video.setOssSrc("url");
        video.setCategoryId(3);
        video.setAuthorId(1365502991892807681L);
        videoService.save(video);
        System.out.println(video);
    }
}
