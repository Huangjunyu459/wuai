package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-03-02
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据传入的作品 id 查询用户名称和评论内容（过审）
     *
     * @param id 作品 id
     * @return 返回结果
     */
    @Select("SELECT username,content FROM COMMENT " +
            "INNER JOIN USER ON comment.user_id = user.id " +
            "WHERE comment.is_delete = 0 " +
            "AND comment.examine = 1  " +
            "AND article_id = #{id}")
    List<UnameAndComment> findUnameAndCommentExamine(String id);

    /**
     * 查找最新的五条审核的评论
     *
     * @param id 作品id
     * @return 返回的结果
     */
    @Select("SELECT * FROM COMMENT " +
            "WHERE examine =1 AND article_id =${id} " +
            "ORDER BY create_time DESC LIMIT 5")
    List<Comment> findFiveCommentExamine(String id);

    /**
     * 根据评论内容模糊查找已删除的评论
     *
     * @param content 评论内容
     * @return 返回的结果
     */
    @Select("SELECT * FROM comment WHERE is_delete = 1 AND content LIKE #{content} ")
    List<Comment> findCommentByContentIsDelete(String content);


    /**
     * 根据id模糊查找已删除的评论
     *
     * @param id 评论id
     * @return 返回的结果
     */
    @Select("SELECT * FROM comment WHERE is_delete = 1 AND id LIKE #{id} ")
    List<Comment> findCommentByTitleByIsDelete(String id);


    /**
     * 根据传入的作品 id 查询用户名称和评论内容（未过审）
     *
     * @param id 作品 id
     * @return 返回结果
     */
    @Select("SELECT article_id,username,content FROM COMMENT " +
            "INNER JOIN USER ON comment.user_id = user.id " +
            "WHERE comment.is_delete = 0 " +
            "AND comment.examine = 0  " +
            "AND article_id = #{id}")
    List<UnameAndComment> findUnameAndCommentNoExamine(String id);

    /**
     * 根据传入的作品 id 查询用户名称和评论内容（已删除）
     *
     * @param id 作品 id
     * @return 返回结果
     */
    @Select("SELECT username,content FROM COMMENT " +
            "INNER JOIN USER ON comment.user_id = user.id " +
            "WHERE comment.is_delete = 1 " +
            "AND article_id = #{id}")
    List<UnameAndComment> findUnameAndCommentIsDelete(String id);


    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Select("select content from comment where user_id = #{id}")
    List<String> findUserComment(String id);

    /**
     * 根据名字模糊查找已删除的评论
     *
     * @param articleId
     * @return 返回的结果
     */
    @Select("SELECT * FROM comment WHERE is_delete = 1 AND article_id LIKE #{articleId} ")
    List<Comment> findCommentByArticleIdIsDelete(String articleId);


    /**
     * 查询已删除的作品
     *
     * @return 返回 Game 的集合
     */
    @Select("select * from comment where is_delete = 1")
    List<Comment> findIsDelete();

    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param id
     * @return
     */
    @Select("select * from comment where is_delete = 1 AND id like #{id} limit #{index} , #{size}")
    List<Comment> pagingQueryIsDelete(String id, Integer index, Integer size);
}
