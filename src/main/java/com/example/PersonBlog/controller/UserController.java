package com.example.PersonBlog.controller;

import com.example.PersonBlog.entity.User;
import com.example.PersonBlog.service.IUserService;
import com.example.PersonBlog.service.Impl.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl UserServiceImpl;
    //登录传入密码和邮箱 返回token(携带用户id)
    @PostMapping("login")
    public String Login(@RequestBody User user){
        return UserServiceImpl.loginUser(user.getUserEmail(),user.getUserPassword());
    }
    //注册传入的是密码 用户名 邮箱
    @PostMapping("register")
    public int register(@RequestBody User user){
        return UserServiceImpl.registerUser(user);
    }
    //根据用户id获取信息
    @PostMapping("infobytoken")
    public User getUserInfoByToken(@RequestBody User user) {
        return UserServiceImpl.getUserInfoByToken(user.getToken());
    }
    @GetMapping("userspace")
    public Long getUserIdByUserName(@RequestParam("userName") String userName){
        return UserServiceImpl.getUserIdByUserName(userName);
    }
    @GetMapping("infobyid")
    public User getUserInfoById(@RequestParam("userId") Long userId){
        return UserServiceImpl.getUserInfoById(userId);
    }
}
