package com.example.PersonBlog.service.Impl;

import DTO.ArticleDTO;
import DTO.RedisData;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.PersonBlog.dao.ArticleListMapper;
import com.example.PersonBlog.dao.ArticleMapper;
import com.example.PersonBlog.entity.Article;
import com.example.PersonBlog.service.IArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleListMapper articleListMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

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
//        Article returnArticle = new Article();
//        String lockKey = "cache:article:" + articleId;
//        // 1. 判断Redis里是否有文章缓存 Key是文章Id，存储对象是一个ArticleDTO对象
//        String articleJson = stringRedisTemplate.opsForValue().get(lockKey);
//        // 2. 有缓存，判断是否逻辑过期
//        if (StrUtil.isNotBlank(articleJson)) {
//            RedisData redisData = JSONUtil.toBean(articleJson, RedisData.class);
//            Article article = JSONUtil.toBean((JSONObject) redisData.getData(),Article.class);
//            LocalDateTime expiredTime  = redisData.getExpiredTime();
//            if (expiredTime.isAfter(LocalDateTime.now())){
//                // 还没有过期
//                return article;
//            }
//            // 已经过期，需要缓存重建
//            // 2.1 获取互斥锁
//            boolean isLock = tryLock(lockKey);
//            // 2.2 判断是否获取锁成功
//            if (isLock) {
//                // 2.3 成功就开启独立线程，实现缓存重建
//
//
//                // 重建完毕，释放锁
//            }
//            //
//        }
//        // 3. 没有缓存，去数据库进行查询
//
//        // 4. 去数据库进行查询，如果有数据，返回时应该设置逻辑过期的缓存
//
//        // 5. 如果没有数据，就是缓存穿透问题，也要返回一个null对象

        return returnArticle;
    }

    /**
     * 获取锁
     * @param lockKey
     * @return
     */
    private boolean tryLock(String lockKey){
        return true;
    }

    /**
     * 释放锁
     * @param lockKey
     */
    private void unLock(String lockKey){

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
