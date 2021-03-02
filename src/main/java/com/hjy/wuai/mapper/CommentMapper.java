package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.UnameAndComment;
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
     * 根据传入的作品 id 查询用户名称和评论内容
     *
     * @param id 作品 id
     * @return 返回结果
     */
    @Select("SELECT username,content FROM COMMENT " +
            "INNER JOIN USER ON comment.user_id = user.id " +
            "WHERE comment.is_delete = 0 " +
            "AND comment.examine = 1  " +
            "AND article_id = #{id}")
    List<UnameAndComment> findUnameAndComment(Long id);


    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Select("select content from comment where user_id = #{id}")
    List<String> findUserComment(Long id);


    /**
     * 查询已删除的作品
     *
     * @return 返回 Game 的集合
     */
    @Select("select * from comment where is_delete = 1")
    List<Comment> findIsDelete();
}
