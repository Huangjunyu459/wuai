package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Video;
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
public interface VideoService extends IService<Video> {

    /**
     * 查询所有视频
     *
     * @return
     */
    public List<Video> findAllVideo();


    /**
     * 根据 视频名 模糊查询
     *
     * @param videoName
     * @return
     */
    public List<Video> findVideoByVideoName(String videoName);

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    public boolean likes(Long id);

    /**
     * 查询已删除的视频
     *
     * @return
     */
    public List<Video> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    public IPage<Video> pagingQuery(Integer index);

}
