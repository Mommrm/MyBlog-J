package com.example.PersonBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String token;
}
