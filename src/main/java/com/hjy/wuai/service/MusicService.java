package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.pojo.Wallpaper;
import org.apache.ibatis.annotations.Select;

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
     * 查询已删除的作品
     *
     * @return 返回的结果
     */
    List<Music> findIsDelete();

    /**
     * 查询最新的十六首歌曲
     *
     * @return 返回的结果
     */
    List<Music> findSixthMusic();

    /**
     * 根据 歌名 模糊查询（过审）
     *
     * @param song 歌名
     * @return 返回的结果
     */
    List<Music> findMusicBySongExamine(String song);

    /**
     * 根据 歌名 模糊查询（未过审）
     *
     * @param song 歌名
     * @return 返回的结果
     */
    List<Music> findMusicBySongNoExamine(String song);

    /**
     * 根据名字模糊查找音频（已删除）
     *
     * @param song 壁纸标题
     * @return 返回的结果
     */
    List<Music> findMusicBySongIsDelete(String song);

    /**
     * 审核功能
     *
     * @param id 音频 id
     * @return 返回的结果
     */
    boolean examine(String id);

    /**
     * 点赞功能
     *
     * @param id 音频 id
     * @return 返回的结果
     */
    boolean likes(String id);

    /**
     * 分页查询（过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Music> pagingQueryExamine(String song, Integer index, Integer size);

    /**
     * 分页查询（未过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Music> pagingQueryNoExamine(String song, Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Music> pagingQueryIsDelete(String song, Integer index, Integer size);

    /**
     * 根据 音频的 id 查询 所属的分类名
     *
     * @param mid 音频 id
     * @return 分类名称
     */
    String findCategoryNameByMid(String mid);

}
