package com.example.PersonBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long articleId;
    private String articleName;
    private String articleContent;
    private String articleAuthor;
    private String articleAbstract;
    private Date articleDate;
    private String articleRule;
    private Long authorId;
    private String articleTag;

}
