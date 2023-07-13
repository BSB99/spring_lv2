package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentLikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("comment/{commentNo}")
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요 API")
    public ResponseEntity<ApiResponseDto> createCommnetLike(@PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto res = commentLikeService.createCommentLike(commentNo, userDetails.getUser());

        return ResponseEntity.status(201).body(res);
    }

    @DeleteMapping("comment/{commentNo}")
    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소 API")
    public ResponseEntity<ApiResponseDto> deleteCommentLike(@PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto res = commentLikeService.deleteCommnetLike(commentNo, userDetails.getUser());

        return ResponseEntity.status(200).body(res);
    }
}
