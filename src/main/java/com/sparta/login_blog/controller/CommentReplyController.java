package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.CommentReplyResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentReplyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reply")
@RequiredArgsConstructor
public class CommentReplyController {
    private final CommentReplyService commentReplyService;

    @PostMapping("/comment/{commentNo}")
    @Operation(summary = "답글 생성", description = "답글 생성 API")
    public CommentReplyResponseDto createCommentReply(@RequestBody CommentRequestDto requestDto, @PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentReplyService.createCommentReply(requestDto.getContent(), commentNo, userDetails.getUser());
    }
}
