package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentReplyResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentReplyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reply")
@RequiredArgsConstructor
public class CommentReplyController {
    private final CommentReplyService commentReplyService;

    @PostMapping("/comment/{commentNo}")
    @Operation(summary = "답글 생성", description = "답글 생성 API")
    public ResponseEntity<CommentReplyResponseDto> createCommentReply(@RequestBody CommentRequestDto request, @PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentReplyResponseDto res = commentReplyService.createCommentReply(request.getContent(), commentNo, userDetails.getUser());

        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("/comment/{commentNo}")
    @Operation(summary = "답글 수정", description = "답글 수정 API")
    public ResponseEntity<CommentReplyResponseDto> updateCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentNo, @RequestBody CommentRequestDto request) {
        CommentReplyResponseDto res = commentReplyService.updateCommentReply(request, userDetails.getUser(), commentNo);

        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/comment/{commentNo}")
    @Operation(summary = "답글 삭제", description = "답글 삭제 API")
    public ResponseEntity<ApiResponseDto> deleteCommentReply(@PathVariable Long commentNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto res = commentReplyService.deleteCommentReply(commentNo, userDetails.getUser());

        return ResponseEntity.status(200).body(res);
    }
}
