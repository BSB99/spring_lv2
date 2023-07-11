package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    private final JwtUtil jwtUtil;
    @PostMapping("/{post_id}/comment")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long post_id, @RequestBody CommentRequestDto request) {
        return commentService.createComment(request, userDetails.getUser(), post_id);
    }

    @PutMapping("/{post_id}/comment/{comment_id}")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long post_id, @PathVariable Long comment_id, @RequestBody CommentRequestDto request) {
        return commentService.updateComment(request, userDetails.getUser(), post_id, comment_id);
    }

    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ApiResponseDto deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long post_id, @PathVariable Long comment_id){
        return commentService.deleteComment(userDetails.getUser(), post_id, comment_id);
    }
}
