package com.example.PersonBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private Long articleId;
    private Long commentatorId;
    private String commentCommentator;
    private Long commentLike;
    private String commentContent;
}
