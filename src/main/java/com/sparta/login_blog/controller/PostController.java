package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.*;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("post")
    @Operation(summary = "게시글 생성", description = "게시글 생성 API")
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
        PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/posts")
    @Operation(summary = "게시글 전체 조회", description = "댓글 전체 조회 API")
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/post/{postNo}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 조회 API")
    public ResponseEntity<PostDetailResponseDto> getPostById(@PathVariable Long postNo) {
        PostDetailResponseDto result = postService.getPostById(postNo);

        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/post/{postNo}")
    @Operation(summary = "게시글 수정", description = "게시글 수정 API")
    public ResponseEntity<PostResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @RequestBody PostRequestDto requestDto) {
        PostResponseDto result = postService.updatePost(postNo, requestDto, userDetails.getUser());

        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/post/{postNo}")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API")
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo) {
        ApiResponseDto result = postService.deletePost(postNo, userDetails.getUser());

        return ResponseEntity.status(200).body(result);
    }
}
