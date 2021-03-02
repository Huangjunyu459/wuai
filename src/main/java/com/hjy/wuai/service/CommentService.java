package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.UnameAndComment;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-03-02
 */
public interface CommentService extends IService<Comment> {

    /**
     * 查询所有评论
     *
     * @return
     */
    List<Comment> findAllComment();


    /**
     * 根据传入的作品 id 查询所有的评论
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    List<UnameAndComment> findUnameAndCommentList(Long id);


    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    List<String> findUserComment(Long id);

    /**
     * 查询已删除的评论
     *
     * @return
     */
    List<Comment> findIsDelete();

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    IPage<Comment> pagingQuery(Integer index);


}
