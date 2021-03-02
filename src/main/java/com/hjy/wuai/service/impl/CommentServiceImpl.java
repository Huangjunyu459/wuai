package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.CommentMapper;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.UnameAndComment;
import com.hjy.wuai.service.CommentService;
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
 * @since 2021-03-02
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 新增评论
     *
     * @param entity 评论实体类
     * @return 返回的结果
     */
    @Override
    public boolean save(Comment entity) {
        Comment comment = new Comment();
        comment.setArticleId(entity.getArticleId());
        comment.setUserId(entity.getUserId());
        comment.setContent(entity.getContent());
        return commentMapper.insert(entity) == 1;
    }


    /**
     * 根据 id 删除
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return commentMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有评论
     *
     * @return 返回的结果
     */
    @Override
    public List<Comment> findAllComment() {
        return commentMapper.selectList(null);
    }


    /**
     * 查询已删除的评论
     *
     * @return 返回的结果
     */
    @Override
    public List<Comment> findIsDelete() {
        return commentMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Comment> pagingQuery(Integer index) {
        IPage<Comment> page = new Page<>(index, 5);
        return commentMapper.selectPage(page, null);
    }


    /**
     * 根据传入的作品 id 查询所有的评论
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public List<UnameAndComment> findUnameAndCommentList(Long id) {
        return commentMapper.findUnameAndComment(id);
    }

    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Override
    public List<String> findUserComment(Long id) {
        return commentMapper.findUserComment(id);
    }

}
