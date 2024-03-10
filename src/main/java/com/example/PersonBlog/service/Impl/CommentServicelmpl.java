package com.example.PersonBlog.service.Impl;

import com.example.PersonBlog.dao.ArticleCommentMapper;
import com.example.PersonBlog.entity.Comment;
import com.example.PersonBlog.service.ICommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServicelmpl implements ICommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();
    //发送评论
    @Override
    public Boolean sentComment(Comment comment) {
        return articleCommentMapper.sentComment(comment);
    }
    //获取文章所有评论
    @Override
    public List<Comment> getCommentsByArticleId(Long articleId) {
        return articleCommentMapper.getCommentsByArticleId(articleId);
    }
    //更新评论点赞数
    public Boolean updataCommentLike(Long commentId, Long userId){
//        //redis要保存两个数据 一个是是否用户对该评论是否点赞 2.是该评论的点赞数量
//        String commentLikedKey = "commentLike:" + commentId + "user: " + userId;
//        String commentLikeNumKey = "commentLikeNum:" + commentId;
//
//        System.out.println("commentId:" + commentId + " userId: " + userId);
//        //1.查看用户是否已经对其评论进行点赞 先在redis进行查找
//        System.out.println(stringRedisTemplate.opsForValue().get(commentLikedKey));
//        //2.如果redis查找失败，去MySQL进行查找，如果没查找到，表明该用户未点赞，返回未点赞 redis进行缓存 下次读取时就可以利用缓存
//        if(!articleCommentMapper.getCommentLiked(commentId,userId)){
//            stringRedisTemplate.opsForValue().set(commentLikedKey,"true");
//            stringRedisTemplate.opsForValue().set(commentLikeNumKey,+1);
//        }
//        //3.

        return false;
    }
}
