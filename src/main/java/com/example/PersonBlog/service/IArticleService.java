package com.example.PersonBlog.service;

import com.example.PersonBlog.entity.Article;

import java.util.List;

public interface IArticleService {
    //创建文章
    boolean createArticle(Article article);
    //获取文章列表 根据分类排序
    List<Article> getArticleList(int listPageNum ,String queryRule ,String method);
    //获取文章内容根据文章Id
    Article getArticleContentById(Long articleId);
    //根据用户Id获取用户所有发布的文章
    List<Article> getArticlesByUserId(Long userId);
    //搜索文章 通过文章名
    List<Article> searchArticleByName(String searchText);
    //删除文章通过文章Id
    boolean deleteArticleById(Long articleId);
}
