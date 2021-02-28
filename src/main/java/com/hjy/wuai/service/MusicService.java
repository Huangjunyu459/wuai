package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Music;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
public interface MusicService extends IService<Music> {

    /**
     * 查询所有作品
     *
     * @return
     */
    public List<Music> findAllMusic();


    /**
     * 根据 作品名 模糊查询
     *
     * @param title
     * @return
     */
    public List<Music> findMusicByTitle(String title);

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
    public List<Music> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    public IPage<Music> pagingQuery(Integer index);

}
