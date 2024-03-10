package com.example.PersonBlog.dao;

import com.example.PersonBlog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//编写匹配原则 1.实体类驼峰匹配 如数据库是user_id 实体类 userId

@Mapper
public interface UserMapper {
    //插入用户
    @Insert("Insert into t_user_baseinfo(user_name,user_password,user_email) values (#{userName} , #{userPassword} , #{userEmail});")
    public boolean insertUser(User user);
    //查找是否有重复的email
    @Select("Select user_email from t_user_baseinfo where user_email = #{user_email};")
    public String selectUserEmail(String user_email);
    //查找是否有重复的用户名
    @Select("Select user_name from t_user_baseinfo where user_name = #{user_name};")
    public String selectUserName(String user_name);
    //密码和账号登录查找
    @Select("Select user_id from t_user_baseinfo where user_email = #{user_email} And user_password = #{user_password};")
    public Long selectUserId(String user_email , String user_password);
    @Select("Select user_id, user_name, user_email from t_user_baseinfo where user_id = #{user_id};")
    public User selectUserInfoById(Long user_id);
    @Select("Select user_id from t_user_baseinfo where user_name = #{userName}")
    public Long getUserIdByUserName(String userName);
}