package com.example.PersonBlog.service;

import com.example.PersonBlog.entity.User;

public interface IUserService {
    //注册用户
    int registerUser(User user);

    String loginUser(String user_email , String password);

    User getUserInfoByToken(String token);
    User getUserInfoById(Long userId);

    Long getUserIdByUserName(String userName);
}
