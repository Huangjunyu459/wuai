package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.pojo.Article;
import com.hjy.wuai.pojo.Video;
import com.hjy.wuai.mapper.VideoMapper;
import com.hjy.wuai.service.VideoService;
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
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 上传
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Video entity) {
        return videoMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取作品
     *
     * @param id
     * @return
     */
    @Override
    public Video getById(Serializable id) {
        return videoMapper.selectById(id);
    }

    /**
     * 作品更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 作品实体
     * @return
     */
    @Override
    public boolean updateById(Video entity) {

        //  获取要更新的用户
        Video video = getById(entity.getId());
        video.setVedioName(entity.getVedioName());
        return videoMapper.updateById(video) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return videoMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有作品
     *
     * @return
     */
    @Override
    public List<Video> findAllVideo() {
        return videoMapper.selectList(null);
    }

    /**
     * 根据 作品名 模糊查询
     *
     * @param videoName
     * @return
     */
    @Override
    public List<Video> findVideoByVideoName(String videoName) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.like("vedio_name", videoName);
        return videoMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(Long id) {
        Video video = videoMapper.selectById(id);
        video.setLove(video.getLove() + 1);
        return videoMapper.updateById(video) == 1 ? true : false;
    }

    /**
     * 查询已删除的视频
     *
     * @return
     */
    @Override
    public List<Video> findIsDelete() {
        return videoMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Video> pagingQuery(Integer index) {
        IPage<Video> page = new Page<>(index, 5);
        IPage<Video> videoIPage = videoMapper.selectPage(page, null);
        return videoIPage;
    }


}
