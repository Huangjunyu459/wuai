package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.mapper.MusicMapper;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

    /**
     * 注入 musicMapper
     */
    @Autowired
    private MusicMapper musicMapper;


    /**
     * 上传
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Music entity) {
        Music music = new Music();
        music.setSinger(entity.getSinger());
        music.setSong(entity.getSong());
        music.setOssSrc(entity.getOssSrc());
        music.setMusicCover(entity.getMusicCover());
        music.setAuthorId(entity.getAuthorId());
        music.setCategoryId(4);
        music.setIsVip(entity.getIsVip());
        return musicMapper.insert(music) == 1;
    }


    /**
     * 根据 id 获取音乐（过审）
     *
     * @param id 音乐 id
     * @return 返回的结果
     */
    @Override
    public Music getById(Serializable id) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return musicMapper.selectById(id);
    }

    /**
     * 根据 id 获取音乐（未过审）
     *
     * @param id 音乐 id
     * @return 返回的结果
     */
    public Music getByIdNoExamine(Serializable id) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).eq("id", id);
        return musicMapper.selectById(id);
    }


    /**
     * 音乐更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 音乐实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Music entity) {

        //  获取要更新的用户
        Music music = getById(entity.getId());
        music.setSinger(entity.getSinger());
        music.setSong(entity.getSong());
        music.setOssSrc(entity.getOssSrc());
        music.setLove(entity.getLove());
        music.setIsVip(entity.getIsVip());
        return musicMapper.updateById(music) == 1;
    }


    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return musicMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有已审核的音乐
     *
     * @return 返回的结果
     */
    @Override
    public List<Music> findAllMusicExamine() {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return musicMapper.selectList(wrapper);
    }

    /**
     * 查询所有未审核的音乐
     *
     * @return 返回的结果
     */
    @Override
    public List<Music> findAllMusicNoExamine() {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return musicMapper.selectList(wrapper);
    }

    /**
     * 查询最新的十六首歌曲
     *
     * @return 返回的结果
     */
    @Override
    public List<Music> findSixthMusic() {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 16");
        return musicMapper.selectList(wrapper);
    }

    /**
     * 审核功能
     *
     * @param id 视频 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(String id) {
        Music music = musicMapper.selectById(id);
        music.setExamine(1);
        return musicMapper.updateById(music) == 1;
    }


    /**
     * 根据 歌曲名 模糊查询(已过审)
     *
     * @param song
     * @return 返回的结果
     */
    @Override
    public List<Music> findMusicBySongExamine(String song) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.like("song", song).eq("examine", 1);
        return musicMapper.selectList(wrapper);
    }

    /**
     * 根据 歌曲名 模糊查询(未过审)
     *
     * @param song
     * @return 返回的结果
     */
    @Override
    public List<Music> findMusicBySongNoExamine(String song) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.like("song", song).eq("examine", 0);
        return musicMapper.selectList(wrapper);
    }

    /**
     * 根据 歌曲名 模糊查询(已删除)
     *
     * @param song
     * @return 返回的结果
     */
    @Override
    public List<Music> findMusicBySongIsDelete(String song) {
        song = "%" + song + "%";
        return musicMapper.findMusicBySongIsDelete(song);
    }

    /**
     * 点赞功能
     *
     * @param id 音频的 id
     * @return 返回的结果
     */
    @Override
    public boolean likes(String id) {
        Music music = musicMapper.selectById(id);
        music.setLove(music.getLove() + 1);
        return musicMapper.updateById(music) == 1;
    }


    /**
     * 查询已删除的作品
     *
     * @return 返回的结果
     */
    @Override
    public List<Music> findIsDelete() {
        return musicMapper.findIsDelete();
    }


    /**
     * 分页查询（过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Music> pagingQueryExamine(String song, Integer index, Integer size) {
        IPage<Music> page = new Page<>(index, size);
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).like("song", song);
        IPage<Music> musicIPage = musicMapper.selectPage(page, wrapper);
        return musicIPage;
    }

    /**
     * 分页查询（未过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Music> pagingQueryNoExamine(String song, Integer index, Integer size) {
        IPage<Music> page = new Page<>(index, size);
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).like("song", song);
        IPage<Music> musicIPage = musicMapper.selectPage(page, wrapper);
        return musicIPage;
    }

    /**
     * 分页查询（已删除）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<Music> pagingQueryIsDelete(String song, Integer index, Integer size) {
        song = "%" + song + "%";
        return musicMapper.pagingQueryIsDelete(song, index, size);
    }


    /**
     * 根据
     * 音频的 id 查询 所属的分类名
     *
     * @param mid 音频id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByMid(String mid) {
        return musicMapper.findCategoryNameByMid(mid);
    }

}
