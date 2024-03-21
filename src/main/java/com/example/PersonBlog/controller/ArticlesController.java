package com.example.PersonBlog.controller;


import DTO.ArticleDTO;
import com.example.PersonBlog.entity.Article;
import com.example.PersonBlog.service.Impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("article")
public class ArticlesController {
    @Autowired
    private ArticleServiceImpl articleServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;
    //创建文章
    @PostMapping("create")
    public boolean createArticle(@RequestBody Article article){
        System.out.println(article);
        return articleServiceImpl.createArticle(article);
    }
    //获取文章列表 根据分类排序
    @GetMapping("getlist")
    public List<Article> getArticleList(@RequestParam("listPageNum") int listPageNum, @RequestParam("queryRule") String queryRule , @RequestParam("showMethod") String method){
        return articleServiceImpl.getArticleList(listPageNum,queryRule,method);
    }

    /**
     * 对查询到的内容进行缓存
     * 需要解决缓存击穿和穿透问题
     * @param articleId
     * @return
     */
    @GetMapping("getcontent")
    public Article getArticleContent(@RequestParam("articleId") Long articleId) {
        return articleServiceImpl.getArticleContentById(articleId);
    }
    //根据用户Id获取用户所有发布的文章
    @GetMapping("getuserarticles")
    public List<Article> getArticlesByUserId(@RequestParam("userId") Long userId){
        System.out.println("userId: " + userId);
        return articleServiceImpl.getArticlesByUserId(userId);
    }
    //搜索文章
    @GetMapping("search")
    public List<Article> searchArticleByName(@RequestParam("searchText") String searchText){
        //1.写入一条Stirng数据
        redisTemplate.opsForValue().set("name","Lucy");
        //2.读取数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name: "  + name);
        return articleServiceImpl.searchArticleByName(searchText);
    }
    //删除文章 根据文章Id
    @DeleteMapping("delete")
    public boolean deleteArticleById(@RequestParam("articleId") Long articleId){
        System.out.println("articleId: " + articleId);
        return articleServiceImpl.deleteArticleById(articleId);
    }
}
