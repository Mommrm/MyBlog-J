package com.example.PersonBlog.service.Impl;
import com.example.PersonBlog.dao.UserMapper;
import com.example.PersonBlog.entity.User;
import com.example.PersonBlog.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements IUserService {
    //token默认七天过期
    private static final long expire = 604800 * 1000;
    //密钥
    private static final String secret = "HelloMommrm";
    @Autowired
    private UserMapper userMapper;
    //用户注册
    @Override
    public int registerUser(User user) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getUserEmail());

        if(!matcher.matches()){
            return 2;
        }
        else {
            //先判断是否已经拥有重复用户名的用户
            if(userMapper.selectUserEmail(user.getUserEmail()) != null){
                return -2; //重复
            }
            else if(userMapper.selectUserName(user.getUserName()) != null){
                return -1;
            }
            if(userMapper.insertUser(user)){
                return 1; //注册成功
            }
            else{
                return 0; //注册失败
            }
        }
    }
    //用户登录
    @Override
    public String loginUser(String user_email , String password){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String token = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user_email);

        if(!matcher.matches()){
            return null;
        }
        else{
            Long uid = (userMapper.selectUserId(user_email , password));
            //查找是否存在此用户
            if(uid != null){
                token = createUserToken(uid);
            }
            return token;
        }

    }
    //获取用户信息根据用户Id
    @Override
    public User getUserInfoById(Long userId){
        return userMapper.selectUserInfoById(userId);
    }
    @Override
    public Long getUserIdByUserName(String userName){
        return userMapper.getUserIdByUserName(userName);
    }

    //生成用户token
    private String createUserToken(Long uid){
        Date now = new Date();
        Date expiration = new Date(now.getTime() +  expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(String.valueOf(uid))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    //根据用户Token获取用户信息
    public User getUserInfoByToken(String token){
        return userMapper.selectUserInfoById(decryptToken(token));
    }

    // 解密token
    public Long decryptToken(String token){
        return Long.valueOf(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
    }
}
