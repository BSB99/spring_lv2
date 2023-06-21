package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.BlogRequestDto;
import com.sparta.login_blog.dto.BlogResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    private final JwtUtil jwtUtil;
    @PostMapping("post")
    public BlogResponseDto createBlog(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody BlogRequestDto requestDto) {
        data = jwtUtil.substringToken(data);
        if (!jwtUtil.validateToken(data)) {
            throw new RuntimeException("Invalid token");
        }
        return blogService.createBlog(requestDto);
    }
}
