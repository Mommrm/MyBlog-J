package com.example.PersonBlog.dao;

import com.example.PersonBlog.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleCommentMapper {
    //创建评论
    @Insert("Insert Into t_article_comment(article_id,commentator_id,comment_content,comment_commentator,comment_like) values (#{articleId} ,#{commentatorId} ,#{commentContent} ,#{commentCommentator} ,#{commentLike})")
    public Boolean sentComment(Comment comment);

    @Select("Select article_id,comment_id,commentator_id,comment_commentator,comment_content,comment_like from t_article_comment where article_id = #{articleId}")
    public List<Comment> getCommentsByArticleId(Long articleId);

    //查看当前用户是否对当前评论已经点赞
    @Select("Select comment_id,liked_user_id from t_comment_like where comment_id = #{commentId} and liked_user_id = #{userId}")
    public Boolean getCommentLiked(Long commentId,Long userId);
}
