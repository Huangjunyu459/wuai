package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Video;
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
public interface VideoService extends IService<Video> {

    /**
     * 查询所有视频
     *
     * @return 返回所有视频的集合
     */
    List<Video> findAllVideo();


    /**
     * 根据 视频名 模糊查询
     *
     * @param videoName 视频的名称
     * @return 返回符合要求的视频的集合
     */
    List<Video> findVideoByVideoName(String videoName);

    /**
     * 点赞功能
     *
     * @param id 视频的 id
     * @return 点赞的结果
     */
    boolean likes(Long id);

    /**
     * 查询已删除的视频
     *
     * @return 返回被删除的视频的集合
     */
    List<Video> findIsDelete();

    /**
     * 分页查询
     *
     * @param index 起始页
     * @return 返回的数据
     */
    IPage<Video> pagingQuery(Integer index);

    /**
     * 根据 视频的 id 查询 所属的分类名
     *
     * @param vid 视频 id
     * @return 分类名称
     */
    String findCategoryNameByVid(Long vid);

}
