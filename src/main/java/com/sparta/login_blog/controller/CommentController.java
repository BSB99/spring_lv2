package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postNo}/comment")
    @Operation(summary = "댓글 생성", description = "댓글 생성 API")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @RequestBody CommentRequestDto request) {
        return commentService.createComment(request, userDetails.getUser(), postNo);
    }

    @PutMapping("/{postNo}/comment/{commentNo}")
    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @PathVariable Long commentNo, @RequestBody CommentRequestDto request) {
        return commentService.updateComment(request, userDetails.getUser(), postNo, commentNo);
    }

    @DeleteMapping("/{postNo}/comment/{commentNo}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    public ApiResponseDto deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @PathVariable Long commentNo){
        return commentService.deleteComment(userDetails.getUser(), postNo, commentNo);
    }
}
