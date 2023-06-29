package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    private final JwtUtil jwtUtil;
    @PostMapping("{post_id}/comment")
    public CommentResponseDto createComment(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data,@PathVariable Long post_id,@RequestBody CommentRequestDto request) {
        data = jwtUtil.doubleCheckToken(data);
        return commentService.createComment(request, data, post_id);
    }

    @PutMapping("{post_id}/comment/{comment_id}")
    public CommentResponseDto updateComment(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data,@PathVariable Long post_id, @PathVariable Long comment_id, @RequestBody CommentRequestDto request) {
        data = jwtUtil.doubleCheckToken(data);
        return commentService.updateComment(request, data, post_id, comment_id);
    }
}
