package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.extension.service.IService;

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
    boolean examine(Long id);


    /**
     * 根据 游戏名 模糊查询
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    List<Game> findGameByGameName(String gameName);

    /**
     * 点赞功能
     *
     * @param id 游戏 id
     * @return 返回的结果
     */
    boolean likes(Long id);

    /**
     * 查询已删除的游戏
     *
     * @return 返回的结果
     */
    List<Game> findIsDelete();

    /**
     * 分页查询
     *
     * @param index 索引号
     * @return 返回的结果
     */
    IPage<Game> pagingQuery(Integer index);

    /**
     * 根据 游戏的 id 查询 所属的分类名
     *
     * @param gid 游戏 id
     * @return 分类名称
     */
    String findCategoryNameByGid(Long gid);

}
