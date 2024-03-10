package com.example.PersonBlog.dao;

import com.example.PersonBlog.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleListMapper {
    //获取全部文章列表
    @Select("SELECT article_id, article_name, article_author, article_abstract, article_date FROM t_article_list LIMIT 10 OFFSET #{listPageNum}")
    public List<Article> getArticleList(int listPageNum);
    //获取全部文章列表 通过时间倒序
    @Select("SELECT article_id, article_name, article_author, article_abstract, article_date FROM t_article_list ORDER BY article_date DESC LIMIT 10 OFFSET #{listPageNum} ")
    public List<Article> getArticleListByTime(int listPageNum);
    //根据文章分类规则获取文章列表
    @Select("SELECT article_id, article_name, article_author, article_abstract, article_date FROM t_article_list Where article_classify = #{rule} LIMIT 10 OFFSET #{listPageNum}")
    public List<Article> getArticleListByRule(int listPageNum,String rule);
    //根据时间排序来获取文章列表
    @Select("SELECT article_id, article_name, article_author, article_abstract, article_date FROM t_article_list Where article_classify = #{rule} ORDER BY article_date DESC LIMIT 10 OFFSET #{listPageNum}")
    public List<Article> getArticleListByTimeAndRule(int listPageNum,String rule);
    //插入文章到文章列表
    @Insert("Insert into t_article_list(article_name,article_author,article_abstract,article_date,article_classify,article_authorId) values (#{articleName},#{articleAuthor},#{articleAbstract},#{articleDate},#{articleRule},#{authorId})")
    public boolean insertArticleToList(Article article);
    //根据文章Id获取具体文章
    @Select("Select article_name,article_author,article_abstract,article_date from t_article_list where article_id = #{articleId}")
    public Article getArticleById(Long articleId);
    //根据用户Id获取文章列表
    @Select("Select article_id , article_name , article_author , article_abstract , article_date FROM t_article_list where article_authorId = #{userId}")
    public List<Article> getArticlesByUserId(Long userId);
    //搜索文章 通过文章名字
    @Select("Select article_id,article_name,article_author,article_abstract,article_date from t_article_list where article_name like CONCAT('%', #{searchText}, '%')")
    public List<Article> searchArticleByName(String searchText);
    @Delete("Delete from t_article_list where article_id = #{articleId}")
    public boolean deleteArticleById(Long articleId);
}
