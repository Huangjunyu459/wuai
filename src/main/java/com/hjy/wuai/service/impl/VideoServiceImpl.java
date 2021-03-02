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

    /**
     * 注入 videoMapper
     */
    @Autowired
    private VideoMapper videoMapper;


    /**
     * 上传视频
     *
     * @param entity 视频实体类
     * @return 返回的结果
     */
    @Override
    public boolean save(Video entity) {
        Video video = new Video();
        video.setVideoName(entity.getVideoName());
        video.setOssName(entity.getOssName());
        video.setOssSrc(entity.getOssSrc());
        video.setAuthorId(entity.getAuthorId());
        video.setCategoryId(entity.getCategoryId());
        return videoMapper.insert(video) == 1;
    }


    /**
     * 根据 id 获取视频
     *
     * @param id 视频的 id
     * @return 返回的结果
     */
    @Override
    public Video getById(Serializable id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return videoMapper.selectOne(wrapper);
    }


    /**
     * 视频更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 视频实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Video entity) {

        //  获取要更新的视频
        Video video = getById(entity.getId());
        video.setVideoName(entity.getVideoName());
        return videoMapper.updateById(video) == 1;
    }


    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return videoMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有已审核的视频
     *
     * @return 返回的结果
     */
    @Override
    public List<Video> findAllVideoExamine() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return videoMapper.selectList(wrapper);
    }


    /**
     * 查询所有未审核视频
     *
     * @return 返回的结果
     */
    @Override
    public List<Video> findAllVideoNoExamine() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return videoMapper.selectList(wrapper);
    }


    /**
     * 审核功能
     *
     * @param id 视频 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(Long id) {
        Video video = videoMapper.selectById(id);
        video.setExamine(1);
        return videoMapper.updateById(video) == 1;
    }


    /**
     * 根据 视频名 模糊查询
     *
     * @param videoName 视频名称
     * @return 返回的结果
     */
    @Override
    public List<Video> findVideoByVideoName(String videoName) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.like("video_name", videoName);
        return videoMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id 视频 id
     * @return 返回的结果
     */
    @Override
    public boolean likes(Long id) {
        Video video = videoMapper.selectById(id);
        video.setLove(video.getLove() + 1);
        return videoMapper.updateById(video) == 1;
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
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Video> pagingQuery(Integer index) {
        IPage<Video> page = new Page<>(index, 5);
        IPage<Video> videoIPage = videoMapper.selectPage(page, null);
        return videoIPage;
    }


    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param vid 视频 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByVid(Long vid) {
        return videoMapper.findCategoryNameByVid(vid);
    }

}
