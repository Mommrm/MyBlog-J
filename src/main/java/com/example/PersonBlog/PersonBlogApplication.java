package com.example.PersonBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.PersonBlog.dao")
public class PersonBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonBlogApplication.class, args);
	}
}
