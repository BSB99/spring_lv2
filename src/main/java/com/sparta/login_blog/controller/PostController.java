package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.PostRequestDto;
import com.sparta.login_blog.dto.PostResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final JwtUtil jwtUtil;
    @PostMapping("post")
    public PostResponseDto createPost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody PostRequestDto requestDto) {
        data = jwtUtil.substringToken(data);
        if (!jwtUtil.validateToken(data)) {
            throw new RuntimeException("Invalid token");
        }
        return postService.createPost(requestDto);
    }

    @GetMapping("posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }
}
