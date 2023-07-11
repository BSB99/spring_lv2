package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.*;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final JwtUtil jwtUtil;
    @PostMapping("post")
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
        System.out.println(userDetails.getUser().getUsername());
        PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDetailResponseDto> getPostById(@PathVariable Long id) {
        PostDetailResponseDto result = postService.getPostById(id);

        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        PostResponseDto result = postService.updatePost(id, requestDto, userDetails.getUser());

        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        ApiResponseDto result = postService.deletePost(id, userDetails.getUser());

        return ResponseEntity.status(200).body(result);
    }
}
