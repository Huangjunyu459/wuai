package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Game;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjy
 * @since 2021-03-01
 */
public interface GameService extends IService<Game> {

    /**
     * 查询所有游戏
     *
     * @return
     */
     List<Game> findAllGame();


    /**
     * 根据 游戏名 模糊查询
     *
     * @param gameName
     * @return
     */
    List<Game> findGameByGameName(String gameName);

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
     boolean likes(Long id);

    /**
     * 查询已删除的游戏
     *
     * @return
     */
     List<Game> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
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
