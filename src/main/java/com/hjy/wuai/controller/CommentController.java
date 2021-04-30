package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.UnameAndComment;
import com.hjy.wuai.pojo.Wallpaper;
import com.hjy.wuai.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class CommentController {

    /**
     * 引入 commentService
     */
    @Autowired
    private CommentServiceImpl commentService;


    /**
     * 新增评论
     *
     * @param comment 评论实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addComment")
    public Result1 addComment(@RequestBody Comment comment) {
        if (commentService.save(comment)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }


    /**
     * 根据 id 获取评论（已过审）
     *
     * @param id 评论 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        Comment comment = commentService.getById(id);
        if (comment != null) {
            return Result1.success().data("comment", comment);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据 id 获取评论（未过审）
     *
     * @param id 评论 id
     * @return 返回的结果 msg
     */
    @GetMapping("getByIdNoExamine")
    public Result1 getByIdNoExamine(String id) {
        Comment comment = commentService.getByIdNoExamine(id);
        if (comment != null) {
            return Result1.success().data("comment", comment);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 更新评论
     *
     * @param comment 评论实体类
     * @return 返回的结果 msg
     */
    @PostMapping("updateCommentById")
    public Result1 updateCommentById(@RequestBody Comment comment) {
        System.out.println("-----------------");
        if (commentService.updateById(comment)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }


    /**
     * 根据 id 删除评论
     *
     * @param id 评论 id
     * @return 返回的结果 msg
     */
    @DeleteMapping("removeCommentById")
    public Result1 removeCommentById(String id) {
        if (commentService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }


    /**
     * 查找最新的五条审核的评论
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findFiveCommentExamine")
    public Result1 findFiveCommentExamine(String id) {
        List<Comment> commentList = commentService.findFiveCommentExamine(id);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 查找所有已审核的评论
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findAllCommentExamine")
    public Result1 findAllCommentExamine() {
        List<Comment> commentList = commentService.findAllCommentExamine();
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 查找所有未审核的评论
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllCommentNoExamine")
    public Result1 findAllCommentNoExamine() {
        List<Comment> commentList = commentService.findAllCommentNoExamine();
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据评论内容模糊查询（已过审）
     *
     * @param content 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByContentExamine")
    public Result1 findCommentByContentExamine(String content) {
        List<Comment> commentList = commentService.findCommentByContentExamine(content);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据评论内容模糊查询（未过审）
     *
     * @param content 评论内容
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByContentNoExamine")
    public Result1 findCommentByContentNoExamine(String content) {
        List<Comment> commentList = commentService.findCommentByContentNoExamine(content);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 根据 评论id 查询（已过审）
     *
     * @param id 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByIdExamine")
    public Result1 findCommentByIdExamine(String id) {
        List<Comment> commentList = commentService.findCommentByIdExamine(id);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据 评论id 查询（未过审）
     *
     * @param id 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByIdNoExamine")
    public Result1 findCommentByIdNoExamine(String id) {
        List<Comment> commentList = commentService.findCommentByIdNoExamine(id);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 根据 评论内容 查询（已删除）
     *
     * @param content 评论内容
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByContentIsDelete")
    public Result1 findCommentByContentIsDelete(String content) {
        List<Comment> commentList = commentService.findCommentByContentIsDelete(content);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }

    /**
     * 根据 评论id 查询（已删除）
     *
     * @param id 壁纸标题
     * @return 返回的结果 msg
     */
    @GetMapping("findCommentByIdIsDelete")
    public Result1 findCommentByIdIsDelete(String id) {
        List<Comment> commentList = commentService.findCommentByIdIsDelete(id);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("评论不存在");
        }
    }


    /**
     * 审核功能
     *
     * @param id 视频的 id
     * @return 返回结果 msg
     */
    @GetMapping("examine")
    public Result1 examine(String id) {
        if (commentService.examine(id)) {
            return Result1.success().setMessage("审核通过");
        } else {
            return Result1.fail().setMessage("审核不通过");
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
     * 评论的分页查询（过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryExamine")
    public Result1 pagingQueryExamine(String content, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable commentIPage = commentService.pagingQueryExamine(content, index, size);
        if (commentIPage != null) {
            return Result1.success().data("commentIPage", commentIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 评论的分页查询（未过审）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryNoExamine")
    public Result1 pagingQueryNoExamine(String content, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable commentIPage = commentService.pagingQueryNoExamine(content, index, size);
        if (commentIPage != null) {
            return Result1.success().data("commentIPage", commentIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 评论的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String content, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Comment> commentList = commentService.pagingQueryIsDelete(content, index, size);
        if (commentList.size() != 0) {
            return Result1.success().data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


    /**
     * 根据传入的作品 id 查询所有的评论（已过审）
     *
     * @param id 作品的id
     * @return 返回的结果
     */
    @RequestMapping("findUnameAndCommentListExamine")
    public Result1 findUnameAndCommentListExamine(String id) {
        List<UnameAndComment> commentList = commentService.findUnameAndCommentListExamine(id);
        if (commentList.size() != 0) {
            return Result1.success().setMessage("获取了用户名和评论内容").data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("该作品还没有用户评论");
        }
    }

    /**
     * 根据传入的作品 id 查询所有的评论（未过审）
     *
     * @param id 作品的id
     * @return 返回的结果
     */
    @RequestMapping("findUnameAndCommentListNoExamine")
    public Result1 findUnameAndCommentListNoExamine(String id) {
        List<UnameAndComment> commentList = commentService.findUnameAndCommentListNoExamine(id);
        if (commentList.size() != 0) {
            return Result1.success().setMessage("获取了用户名和评论内容").data("commentList", commentList);
        } else {
            return Result1.fail().setMessage("该作品还没有用户评论");
        }
    }

    /**
     * 根据传入的作品 id 查询所有的评论（已删除）
     *
     * @param id 作品的id
     * @return 返回的结果
     */
    @RequestMapping("findUnameAndCommentListIsDelete")
    public Result1 findUnameAndCommentListIsDelete(String id) {
        List<UnameAndComment> commentList = commentService.findUnameAndCommentListIsDelete(id);
        if (commentList.size() != 0) {
            return Result1.success().setMessage("获取了用户名和评论内容").data("commentList", commentList);
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
    public Result1 findUserComment(String id) {
        List<String> commentList = commentService.findUserComment(id);
        if (commentList.size() == 0) {
            return Result1.fail().setMessage("该用户还没有过任何的评论").data("statue", false);
        } else {
            return Result1.success().setMessage("用户存在评论记录,且记录名为 userCommentList").data("commentList", commentList);
        }
    }

}

