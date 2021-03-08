package com.hjy;

import com.hjy.wuai.controller.MusicController;
import com.hjy.wuai.mapper.MusicMapper;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.MusicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,下午 22:55
 * @email: 541605007@qq.com
 */
@SpringBootTest
public class MusicTest {

    @Autowired
    private MusicController musicController;

    @Autowired
    private MusicServiceImpl musicService;

    @Autowired
    private MusicMapper musicMapper;

    @Test
    void testFindAll() {
        System.out.println(musicController.findAllMusicExamine());
        List<Music> wallpaperList = musicService.findAllMusicExamine();
        Iterator<Music> iterator = wallpaperList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getSong());
        }
    }


}
