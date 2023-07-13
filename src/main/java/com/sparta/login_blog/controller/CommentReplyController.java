package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.CommentReplyResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
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
    public CommentReplyResponseDto createCommentReply(@RequestBody CommentRequestDto request, @PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentReplyService.createCommentReply(request.getContent(), commentNo, userDetails.getUser());
    }

    @PutMapping("/comment/{commentNo}")
    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    public CommentReplyResponseDto updateCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentNo, @RequestBody CommentRequestDto request) {
        return commentReplyService.updateCommentReply(request, userDetails.getUser(), commentNo);
    }
}
