package com.example.PersonBlog;

import com.example.PersonBlog.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisStringTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        //写入数据
        stringRedisTemplate.opsForValue().set("name","虎哥");
        //获取数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testSaveUser() throws JsonProcessingException {
        //创建对象
        User user = new User(1L,"Mommrm","123456","1295821492@qq.com","1");
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        stringRedisTemplate.opsForValue().set("user:1",json);
        //获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:1");
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1: " + user1);
    }

}
