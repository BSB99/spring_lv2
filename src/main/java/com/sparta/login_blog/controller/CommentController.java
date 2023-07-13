package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postNo}/comment")
    @Operation(summary = "댓글 생성", description = "댓글 생성 API")
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @RequestBody CommentRequestDto request) {
        CommentResponseDto res =  commentService.createComment(request, userDetails.getUser(), postNo);

        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("/{postNo}/comment/{commentNo}")
    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    public ResponseEntity<CommentResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @PathVariable Long commentNo, @RequestBody CommentRequestDto request) {
        CommentResponseDto res = commentService.updateComment(request, userDetails.getUser(), postNo, commentNo);

        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/{postNo}/comment/{commentNo}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNo, @PathVariable Long commentNo){
        ApiResponseDto res = commentService.deleteComment(userDetails.getUser(), postNo, commentNo);

        return ResponseEntity.status(200).body(res);
    }
}
