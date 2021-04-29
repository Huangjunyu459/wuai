package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.CommentMapper;
import com.hjy.wuai.pojo.*;
import com.hjy.wuai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /**
     * 注入 commentMapper
     */
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
        comment.setUserName(entity.getUserName());
        comment.setContent(entity.getContent());
        return commentMapper.insert(entity) == 1;
    }

    /**
     * 根据 id 获取评论（过审）
     *
     * @param id 视频的 id
     * @return 返回的结果
     */
    @Override
    public Comment getById(Serializable id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return commentMapper.selectOne(wrapper);
    }

    /**
     * 根据 id 获取评论（未过审）
     *
     * @param id 视频的 id
     * @return 返回的结果
     */
    public Comment getByIdNoExamine(Serializable id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).eq("id", id);
        return commentMapper.selectOne(wrapper);
    }

    /**
     * 更新评论
     *
     * @param entity 用户实体类
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Comment entity) {
        System.out.println("----------------------");
        Comment comment = getById(entity.getId());
        comment.setArticleId(entity.getArticleId());
        comment.setUserId(entity.getUserId());
        comment.setContent(entity.getContent());
        System.out.println(entity);
        return commentMapper.updateById(comment) == 1;
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
     * 查询所有已审核的评论
     *
     * @return 返回的结果
     */
    @Override
    public List<Comment> findAllCommentExamine() {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return commentMapper.selectList(wrapper);
    }

    /**
     * 查找最新的五条审核的评论
     *
     * @return 返回的结果
     */
    @Override
    public List<Comment> findFiveCommentExamine(String id) {
        return commentMapper.findFiveCommentExamine(id);
    }


    /**
     * 查询所有未审核的评论
     *
     * @return 返回的结果
     */
    @Override
    public List<Comment> findAllCommentNoExamine() {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return commentMapper.selectList(wrapper);
    }


    /**
     * 根据 评论id 模糊查询(已过审)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    @Override
    public List<Comment> findCommentByIdExamine(String id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.like("id", id).eq("examine", 1);
        return commentMapper.selectList(wrapper);
    }

    /**
     * 根据 评论id 模糊查询(未过审)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    @Override
    public List<Comment> findCommentByIdNoExamine(String id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.like("id", id).eq("examine", 0);
        return commentMapper.selectList(wrapper);
    }

    /**
     * 根据 评论id 模糊查询(已删除)
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    @Override
    public List<Comment> findCommentByIdIsDelete(String id) {
        id = "%" + id + "%";
        return commentMapper.findCommentByTitleByIsDelete(id);
    }


    /**
     * 审核功能
     *
     * @param id 评论 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(String id) {
        Comment comment = commentMapper.selectById(id);
        comment.setExamine(1);
        return commentMapper.updateById(comment) == 1;
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
     * 分页查询（过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Comment> pagingQueryExamine(String id, Integer index, Integer size) {
        IPage<Comment> page = new Page<>(index, size);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).like("id", id);
        return commentMapper.selectPage(page, wrapper);
    }

    /**
     * 分页查询（未过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Comment> pagingQueryNoExamine(String id, Integer index, Integer size) {
        IPage<Comment> page = new Page<>(index, size);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).like("id", id);
        return commentMapper.selectPage(page, wrapper);
    }

    /**
     * 分页查询（已删除）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<Comment> pagingQueryIsDelete(String id, Integer index, Integer size) {
        id = "%" + id + "%";
        return commentMapper.pagingQueryIsDelete(id,index, size);
    }


    /**
     * 根据传入的作品 id 查询所有的评论（过审）
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public List<UnameAndComment> findUnameAndCommentListExamine(String id) {
        return commentMapper.findUnameAndCommentExamine(id);
    }

    /**
     * 根据传入的作品 id 查询所有的评论（未过审）
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public List<UnameAndComment> findUnameAndCommentListNoExamine(String id) {
        return commentMapper.findUnameAndCommentNoExamine(id);
    }

    /**
     * 根据传入的作品 id 查询所有的评论（已删除）
     *
     * @param id 作品 id
     * @return 返回的结果
     */
    @Override
    public List<UnameAndComment> findUnameAndCommentListIsDelete(String id) {
        return commentMapper.findUnameAndCommentNoExamine(id);
    }


    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Override
    public List<String> findUserComment(String id) {
        return commentMapper.findUserComment(id);
    }

}
