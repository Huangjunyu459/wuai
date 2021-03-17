package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-03-01
 */
@RestController
@RequestMapping("/game")
@CrossOrigin
public class GameController {

    /**
     * 引入 gameService
     */
    @Autowired
    private GameServiceImpl gameService;


    /**
     * 上传游戏
     *
     * @param game 游戏实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addGame")
    public Result1 addGame(@RequestBody Game game) {
        if (gameService.saveByAdmin(game)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取游戏
     *
     * @param id 游戏 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Game game = gameService.getById(id);
        if (game != null) {
            return Result1.success().data("game", game);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }

    /**
     * 根据 id 获取游戏(未过审)
     *
     * @param id 游戏 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Game game = gameService.getByIdNoExamine(id);
        if (game != null) {
            return Result1.success().data("game", game);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 更新游戏信息
     *
     * @param game 游戏实体
     * @return 返回的结果 msg
     */
    @PostMapping("updateById")
    public Result1 updateById(@RequestBody Game game) {
        if (gameService.updateById(game)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }


    /**
     * 根据 id 删除游戏
     *
     * @param id 游戏 id
     * @return 返回的结果 msg
     */
    @DeleteMapping("removeGameById")
    public Result1 removeGameById(String id) {
        if (gameService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查找所有已审核的游戏
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllGameExamine")
    public Result1 findAllGameExamine() {
        List<Game> gameList = gameService.findAllGameExamine();
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 查找所有未审核的游戏
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findAllGameNoExamine")
    public Result1 findAllGameNoExamine() {
        List<Game> gameList = gameService.findAllGameNoExamine();
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }

    /**
     * 查找最新的八部游戏
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findEightGame")
    public Result1 findEightGame() {
        List<Game> gameList = gameService.findEightGame();
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }

    /**
     * 查找最新的十六部游戏
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findSixthGame")
    public Result1 findSixthGame() {
        List<Game> gameList = gameService.findSixthGame();
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 审核功能
     *
     * @param id 视频的 id
     * @return 返回结果 msg
     */
    @GetMapping("examine")
    public Result1 examine(String id) {
        if (gameService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
        }
    }


    /**
     * 根据 游戏名称 查询（已过审的）
     *
     * @param gameName 游戏名称
     * @return 返回的结果 msg
     */
    @GetMapping("findGameByGameNameExamine")
    public Result1 findGameByGameNameExamine(String gameName) {
        List<Game> gameList = gameService.findGameByGameNameExamine(gameName);
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }

    /**
     * 根据 游戏名称 查询（未过审的）
     *
     * @param gameName 游戏名称
     * @return 返回的结果 msg
     */
    @GetMapping("findGameByGameNameNoExamine")
    public Result1 findGameByGameNameNoExamine(String gameName) {
        List<Game> gameList = gameService.findGameByGameNameNoExamine(gameName);
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 根据 游戏名称 查询（已删除）
     *
     * @param gameName 游戏名称
     * @return 返回的结果 msg
     */
    @GetMapping("findGameByGameNameIsDelete")
    public Result1 findGameByGameNameIsDelete(String gameName) {
        List<Game> gameList = gameService.findGameByGameNameIsDelete(gameName);
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 游戏点赞
     *
     * @param id 游戏的 id
     * @return 返回的结果 msg
     */
    @GetMapping("likes")
    public Result1 likes(String id) {
        if (gameService.likes(id)) {
            return Result1.success().setMessage("点赞成功");
        } else {
            return Result1.fail().setMessage("点赞失败");
        }
    }


    /**
     * 查询已删除的游戏
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Game> gameList = gameService.findIsDelete();
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("游戏不存在");
        }
    }


    /**
     * 分类的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String gameName, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable gameIPage = gameService.pagingQueryExamine(gameName, index, size);
        if (gameIPage != null) {
            return Result1.success().data("gameIPage", gameIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 分类的分页查询（未过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryNoExamine")
    public Result1 pagingQueryNoExamine(String gameName, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable gameIPage = gameService.pagingQueryNoExamine(gameName, index, size);
        if (gameIPage != null) {
            return Result1.success().data("gameIPage", gameIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 分类的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String gameName, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Game> gameList = gameService.pagingQueryIsDelete(gameName, index, size);
        if (gameList.size() != 0) {
            return Result1.success().data("gameList", gameList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据 游戏 id 查询所属分类
     *
     * @param gid 游戏的 id
     * @return 返回的结果 msg
     */
    @GetMapping("findCategoryNameByGid")
    public Result1 findCategoryNameByGid(String gid) {
        String categoryName = gameService.findCategoryNameByGid(gid);
        if (categoryName != null) {
            return Result1.success().data("categoryName", categoryName);
        } else {
            return Result1.fail().setMessage("没有所属分类名称");
        }
    }

}

