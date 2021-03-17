package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.Wallpaper;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-03-01
 */
public interface GameService extends IService<Game> {

    /**
     * 查询所有已审核的游戏
     *
     * @return 返回的结果
     */
    List<Game> findAllGameExamine();

    /**
     * 查询所有未审核的游戏
     *
     * @return 返回的结果
     */
    List<Game> findAllGameNoExamine();

    /**
     * 审核功能
     *
     * @param id 游戏 id
     * @return 返回的结果
     */
    boolean examine(String id);


    /**
     * 根据 游戏名 模糊查询（已过审）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    List<Game> findGameByGameNameExamine(String gameName);

    /**
     * 根据 游戏名 模糊查询（未过审）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    List<Game> findGameByGameNameNoExamine(String gameName);

    /**
     * 根据分类名 模糊查询（已删除的游戏）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    List<Game> findGameByGameNameIsDelete(String gameName);

    /**
     * 查询最新的八部游戏
     *
     * @return 返回的结果
     */
    List<Game> findEightGame();

    /**
     * 查询最新的十六部游戏
     *
     * @return 返回的结果
     */
    List<Game> findSixthGame();

    /**
     * 点赞功能
     *
     * @param id 游戏 id
     * @return 返回的结果
     */
    boolean likes(String id);

    /**
     * 查询已删除的游戏
     *
     * @return 返回的结果
     */
    List<Game> findIsDelete();


    /**
     * 分页查询（过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Game> pagingQueryExamine(String gameName, Integer index, Integer size);

    /**
     * 分页查询（未过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Game> pagingQueryNoExamine(String gameName, Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Game> pagingQueryIsDelete(String gameName, Integer index, Integer size);

    /**
     * 根据 游戏的 id 查询 所属的分类名
     *
     * @param gid 游戏 id
     * @return 分类名称
     */
    String findCategoryNameByGid(String gid);

}
