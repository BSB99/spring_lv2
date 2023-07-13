package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.PostLikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/like")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/post/{postNo}")
    @Operation(summary = "게시글 좋아요 등록", description = "게시글 좋아요 등록 API")
    public ResponseEntity<ApiResponseDto> createPostLike(@PathVariable Long postNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto res = postLikeService.createPostLike(postNo, userDetails.getUser());

        return ResponseEntity.status(201).body(res);
    }

    @DeleteMapping("/post/{postNo}")
    @Operation(summary = "게시글 좋아요 취소", description = "게시글 좋아요 취소 API")
    public ResponseEntity<ApiResponseDto> deletePostLike(@PathVariable Long postNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto res = postLikeService.deletePostLike(postNo, userDetails.getUser());

        return ResponseEntity.status(200).body(res);
    }
}
