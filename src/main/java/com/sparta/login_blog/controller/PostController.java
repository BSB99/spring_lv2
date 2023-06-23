package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.PostListResponseDto;
import com.sparta.login_blog.dto.PostRequestDto;
import com.sparta.login_blog.dto.PostResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.service.PostService;
import jakarta.websocket.server.PathParam;
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
        jwtUtil.doubleCheckToken(data);
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts")
    public PostListResponseDto getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        jwtUtil.doubleCheckToken(data);
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public ApiResponseDto deletePost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long id) {
        jwtUtil.doubleCheckToken(data);
        return postService.deletePost(id);
    }
}
