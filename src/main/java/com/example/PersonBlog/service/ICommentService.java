package com.example.PersonBlog.service;

import com.example.PersonBlog.entity.Comment;

import java.util.List;

public interface ICommentService {
    //创建评论
    Boolean sentComment(Comment comment);

    //根据文章ID 获取文章所有评论
    List<Comment> getCommentsByArticleId(Long articleId);

    //更新评论点赞数
    Boolean updataCommentLike(Long commentId,Long userId);
}
