package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.UnameAndComment;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 新增评论
     *
     * @param comment 评论实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("save")
    public Result1 save(Comment comment) {
        if (commentService.save(comment)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 删除评论
     *
     * @param id 评论 id
     * @return 返回的结果 msg
     */
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
        if (commentService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }

    /**
     * 查找所有评论
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllComment")
    public Result1 findAllComment() {
        List<Comment> commentList = commentService.findAllComment();
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 查询已删除的评论
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Comment> commentList = commentService.findIsDelete();
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 评论分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable commentIPage = commentService.pagingQuery(index);
        if (commentIPage != null) {
            return Result1.success().data("commentIPage", commentIPage);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据传入的作品 id 查询所有的评论
     *
     * @param id 作品的id
     * @return 返回的结果
     */
    @RequestMapping("findUnameAndCommentList")
    public Result1 findUnameAndCommentList(Long id) {
        List<UnameAndComment> unameAndCommentList = commentService.findUnameAndCommentList(id);
        if (unameAndCommentList.size() != 0) {
            return Result1.success().setMessage("获取了用户名和评论内容").data("unameAndCommentList", unameAndCommentList);
        } else {
            return Result1.fail().setMessage("该作品还没有用户评论");
        }
    }


    /**
     * 根据传入的用户 id 查询该用户的所有评论
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @RequestMapping("findUserComment")
    public Result1 findUserComment(Long id) {
        List<String> userCommentList = commentService.findUserComment(id);
        if (userCommentList.size() == 0) {
            return Result1.fail().setMessage("该用户还没有过任何的评论").data("statue", false);
        } else {
            return Result1.success().setMessage("用户存在评论记录,且记录名为 userCommentList").data("userCommentList", userCommentList);
        }
    }

}

