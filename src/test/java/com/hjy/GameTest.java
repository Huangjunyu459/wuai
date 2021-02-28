package com.hjy;

import com.hjy.wuai.controller.GameController;
import com.hjy.wuai.mapper.GameMapper;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/28 0028,下午 23:10
 * @email: 541605007@qq.com
 */
@SpringBootTest
public class GameTest {

    @Autowired
    private GameController gameController;

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private GameMapper gameMapper;

    @Test
    void testFindAll() {
        System.out.println(gameController.findAllGame());
        List<Game> gameList = gameService.findAllGame();
        Iterator<Game> iterator = gameList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    void testGameMapper(){
        List<Game> gameList = gameMapper.findAll();
        gameList.forEach(System.out::println);
    }


    @Test
    void testPage() {
        gameController.pagingQuery(1);
    }
}
