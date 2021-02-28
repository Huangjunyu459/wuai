package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("/save")
    public String save(Game entity) {
        return gameService.save(entity) == true ? "success" : "fail";
    }

    @PostMapping("upload")
    public String upload(Game game) {
        return gameService.save(game) == true ? "admin" : "fail";
    }

    @GetMapping("getById")
    public String getById(Long id) {
        Game game = gameService.getById(id);
        return "admin";
    }

    @PostMapping("updateById")
    public String updateById(Game game) {
        return gameService.updateById(game) == true ? "admin" : "fail";
    }

    @GetMapping("removeById")
    public String removeById(Long id) {
        return gameService.removeById(id) == true ? "admin" : "fail";
    }

    @GetMapping("/findAllGame")
    public String findAllGame() {
        List<Game> gameList = gameService.findAllGame();
        return "admin";
    }

    @GetMapping("findGameByTitle")
    public String findGameByTitle(String title) {
        List<Game> gameList = gameService.findGameByTitle(title);
        return "admin";
    }

    @GetMapping("likes")
    public String likes(Long id) {
        return gameService.likes(id) == true ? "admin" : "fail";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<Game> gameList = gameService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable gameIPage = gameService.pagingQuery(index);
        System.out.println(gameIPage);
        return "admin";
    }

}

