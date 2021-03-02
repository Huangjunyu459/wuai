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
     * 查询所有已审核的音乐
     *
     * @return 返回的结果
     */
    List<Music> findAllMusicExamine();

    /**
     * 查询所有未审核的音乐
     *
     * @return 返回的结果
     */
    List<Music> findAllMusicNoExamine();


    /**
     * 审核功能
     *
     * @param id 音频 id
     * @return 返回的结果
     */
    boolean examine(Long id);


    /**
     * 根据 歌名 模糊查询
     *
     * @param song 歌名
     * @return 返回的结果
     */
    List<Music> findMusicBySong(String song);

    /**
     * 点赞功能
     *
     * @param id 音频 id
     * @return 返回的结果
     */
    boolean likes(Long id);

    /**
     * 查询已删除的作品
     *
     * @return 返回的结果
     */
    List<Music> findIsDelete();

    /**
     * 分页查询
     *
     * @param index 索引号
     * @return 返回的结果
     */
    IPage<Music> pagingQuery(Integer index);

    /**
     * 根据 音频的 id 查询 所属的分类名
     *
     * @param mid 音频 id
     * @return 分类名称
     */
    String findCategoryNameByMid(Long mid);

}
