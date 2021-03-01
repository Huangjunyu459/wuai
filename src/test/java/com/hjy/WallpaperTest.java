package com.hjy;

import com.hjy.wuai.controller.WallpaperController;
import com.hjy.wuai.mapper.WallpaperMapper;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.WallpaperServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,下午 22:08
 * @email: 541605007@qq.com
 */
@SpringBootTest
public class WallpaperTest {

    @Autowired
    private WallpaperController wallpaperController;

    @Autowired
    private WallpaperServiceImpl wallpaperService;

    @Autowired
    private WallpaperMapper wallpaperMapper;

    @Test
    void testFindAll() {
        List<Wallpaper> wallpaperList = wallpaperService.findAllWallpaper();
        Iterator<Wallpaper> iterator = wallpaperList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    @Test
    void testPage() {

        wallpaperController.pagingQuery(1);
    }
}
