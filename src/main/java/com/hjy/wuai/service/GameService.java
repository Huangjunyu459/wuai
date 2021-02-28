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
 * @since 2021-02-28
 */
public interface GameService extends IService<Game> {

    /**
     * 查询所有作品
     *
     * @return
     */
    public List<Game> findAllGame();


    /**
     * 根据 作品名 模糊查询
     *
     * @param title
     * @return
     */
    public List<Game> findGameByTitle(String title);

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    public boolean likes(Long id);

    /**
     * 查询已删除的作品
     *
     * @return
     */
    public List<Game> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    public IPage<Game> pagingQuery(Integer index);

}
