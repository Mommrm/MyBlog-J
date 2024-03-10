package com.example.PersonBlog.service.Impl;

import com.example.PersonBlog.dao.ArticleListMapper;
import com.example.PersonBlog.dao.ArticleMapper;
import com.example.PersonBlog.entity.Article;
import com.example.PersonBlog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleListMapper articleListMapper;
    //用户发布文章
    @Override
    public boolean createArticle(Article article){
        return articleMapper.insertArticle(article) && articleListMapper.insertArticleToList(article);
    }
    //获取文章列表 根据分类排序
    @Override
    public List<Article> getArticleList(int listPageNum, String queryRule ,String method) {
        //默认情况 1.综合＋推荐 2.综合＋时间排序 全表查询
        if (queryRule.equals("comprehensive")) {
            if(method.equals("recommend")){
                return articleListMapper.getArticleList(listPageNum);
            }
            else if (method.equals("now")) {
                return articleListMapper.getArticleListByTime(listPageNum);
            }
        }
        else {
            if (method.equals("recommend")) {
                return articleListMapper.getArticleListByRule(listPageNum, queryRule);
            }
            else if (method.equals("now")) {
                return articleListMapper.getArticleListByTimeAndRule(listPageNum, queryRule);
            }
        }
        return null;
    }
    //获取文章内容通过文章Id
    @Override
    public Article getArticleContentById(Long articleId){
        //填入返回的文章
        Article tempA = articleListMapper.getArticleById(articleId);// 有name author abstract date tag authorId
        Article tempB = articleMapper.getArticleById(articleId);  //id content tag
        //根据Id获取文章全部内容
        Article returnArticle = new Article();
        returnArticle.setArticleId(articleId);
        returnArticle.setArticleContent(tempB.getArticleContent());
        returnArticle.setArticleAuthor(tempA.getArticleAuthor());
        returnArticle.setArticleName(tempA.getArticleName());
        returnArticle.setArticleAbstract(tempA.getArticleAbstract());
        returnArticle.setArticleDate(tempA.getArticleDate());
        returnArticle.setArticleTag(tempB.getArticleTag());

        return returnArticle;
    }
    //获取用户发布的所有文章
    @Override
    public List<Article> getArticlesByUserId(Long userId) {
        return articleListMapper.getArticlesByUserId(userId);
    }
    //搜索文章通过文章名字
    public List<Article> searchArticleByName(String searchText){
        return articleListMapper.searchArticleByName(searchText);
    }
    //删除文章 根据文章Id
    public boolean deleteArticleById(Long articleId){
        return (articleListMapper.deleteArticleById(articleId) && articleMapper.deleteArticleById(articleId));
    }
}
