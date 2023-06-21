package com.sparta.login_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LoginBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginBlogApplication.class, args);
    }

}
