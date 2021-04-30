package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.UnameAndComment;
import com.hjy.wuai.pojo.Wallpaper;

import java.io.Serializable;
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
     * 查询所有已审核的评论
     *
     * @return 返回的结果
     */
    List<Comment> findAllCommentExamine();

    /**
     * 查找最新的五条审核的评论
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    List<Comment> findFiveCommentExamine(String id);


    /**
     * 查询所有未审核的评论
     *
     * @return 返回的结果
     */
    List<Comment> findAllCommentNoExamine();

    /**
     * 查询已删除的评论
     *
     * @return 返回的结果
     */
    List<Comment> findIsDelete();


    /**
     * 根据传入的作品 id 查询所有的评论(已过审)
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    List<UnameAndComment> findUnameAndCommentListExamine(String id);

    /**
     * 根据传入的作品 id 查询所有的评论(已过审)
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    List<UnameAndComment> findUnameAndCommentListNoExamine(String id);

    /**
     * 根据传入的作品 id 查询所有的评论(已过审)
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    List<UnameAndComment> findUnameAndCommentListIsDelete(String id);


    /**
     * 根据 评论id 模糊查询(已过审)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    List<Comment> findCommentByIdExamine(String id);

    /**
     * 根据 评论内容 模糊查询(已过审)
     *
     * @param content 评论内容
     * @return 返回的结果
     */
    List<Comment> findCommentByContentExamine(String content);

    /**
     * 根据 评论内容 模糊查询(未过审)
     *
     * @param content 评论内容
     * @return 返回的结果
     */
    List<Comment> findCommentByContentNoExamine(String content);

    /**
     * 根据 评论id 模糊查询(未过审)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    List<Comment> findCommentByIdNoExamine(String id);

    /**
     * 根据 评论内容 模糊查询(已删除)
     *
     * @param content 评论内容
     * @return 返回的结果
     */
    List<Comment> findCommentByContentIsDelete(String content);

    /**
     * 根据 评论id 模糊查询(已删除)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    public List<Comment> findCommentByIdIsDelete(String id);

    /**
     * 审核功能
     *
     * @param id 游戏 id
     * @return 返回的结果
     */
    boolean examine(String id);

    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    List<String> findUserComment(String id);


    /**
     * 分页查询（过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Comment> pagingQueryExamine(String id, Integer index, Integer size);

    /**
     * 分页查询（未过审）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Comment> pagingQueryNoExamine(String id, Integer index, Integer size);

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Comment> pagingQueryIsDelete(String id, Integer index, Integer size);

}
