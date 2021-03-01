package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.mapper.MusicMapper;
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
        return musicMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取音乐
     *
     * @param id
     * @return
     */
    @Override
    public Music getById(Serializable id) {
        return musicMapper.selectById(id);
    }

    /**
     * 音乐更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 音乐实体
     * @return
     */
    @Override
    public boolean updateById(Music entity) {

        //  获取要更新的用户
        Music music = getById(entity.getId());
        music.setSinger(entity.getSinger());
        music.setSong(entity.getSong());
        return musicMapper.updateById(music) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return musicMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有音乐
     *
     * @return
     */
    @Override
    public List<Music> findAllMusic() {
        return musicMapper.selectList(null);
    }

    /**
     * 根据 歌曲名 模糊查询
     *
     * @param song
     * @return
     */
    @Override
    public List<Music> findMusicBySong(String song) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.like("song", song);
        return musicMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(Long id) {
        Music music = musicMapper.selectById(id);
        music.setLove(music.getLove() + 1);
        return musicMapper.updateById(music) == 1 ? true : false;
    }

    /**
     * 查询已删除的作品
     *
     * @return
     */
    @Override
    public List<Music> findIsDelete() {
        return musicMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Music> pagingQuery(Integer index) {
        IPage<Music> page = new Page<>(index, 5);
        IPage<Music> musicIPage = musicMapper.selectPage(page, null);
        return musicIPage;
    }

    /**
     * 根据
     * 音频的 id 查询 所属的分类名
     *
     * @param mid
     * 音频id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByMid(Long mid) {
        return musicMapper.findCategoryNameByMid(mid);
    }
}
