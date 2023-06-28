package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.PostListResponseDto;
import com.sparta.login_blog.dto.PostRequestDto;
import com.sparta.login_blog.dto.PostResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.service.PostService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final JwtUtil jwtUtil;
    @PostMapping("post")
    public ResponseEntity<PostResponseDto> createPost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody PostRequestDto requestDto) {
        data = jwtUtil.doubleCheckToken(data);
        PostResponseDto result = postService.createPost(requestDto, data);

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto result = postService.getPostById(id);

        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        data = jwtUtil.doubleCheckToken(data);
        PostResponseDto result = postService.updatePost(id, requestDto, data);

        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long id) {
        data = jwtUtil.doubleCheckToken(data);
        ApiResponseDto result = postService.deletePost(id, data);

        return ResponseEntity.status(200).body(result);
    }
}
