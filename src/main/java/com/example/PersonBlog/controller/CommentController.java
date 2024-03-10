package com.example.PersonBlog.controller;

import com.example.PersonBlog.dao.ArticleCommentMapper;
import com.example.PersonBlog.entity.Comment;
import com.example.PersonBlog.service.Impl.CommentServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentServicelmpl commentServicelmpl;
    //发送评论
    @PostMapping("sent")
    public Boolean sentComment(@RequestBody Comment comment) {
        return commentServicelmpl.sentComment(comment);
    }
    //获取文章评论
    @GetMapping("getcomment")
    public List<Comment> getCommentsByArticleId(@RequestParam("articleId") Long articleId){
        return commentServicelmpl.getCommentsByArticleId(articleId);
    }
    //更新点赞数
    @PutMapping("like")
    public boolean updataCommentLike(@RequestParam("commentId") Long commentId,@RequestParam("userId") Long userId){
        return commentServicelmpl.updataCommentLike(commentId,userId);
    }

}
