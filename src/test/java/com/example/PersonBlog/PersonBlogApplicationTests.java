package com.example.PersonBlog;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class PersonBlogApplicationTests {
	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("name","虎哥");

		Object name = redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}
}
