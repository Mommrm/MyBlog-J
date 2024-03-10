package com.example.PersonBlog.dao;

import com.example.PersonBlog.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {
    //根据文章Id查找文章内容
    @Select("Select article_id , article_content,article_tag from t_article_content where article_id = #{articleId}")
    public Article getArticleById(Long articleId);
    //创建文章 插入内容
    @Insert("Insert Into t_article_content(article_content,article_tag) values (#{articleContent},#{articleTag})")
    public boolean insertArticle(Article article);
    //删除文章 根据文章Id
    @Delete("Delete from t_article_content where article_id = #{articleId}")
    public boolean deleteArticleById(Long articleId);
}
