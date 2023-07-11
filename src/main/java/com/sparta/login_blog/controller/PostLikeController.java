package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.security.UserDetailsImpl;
import com.sparta.login_blog.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/like")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/post/{postNo}")
    public ApiResponseDto createPostLike(@PathVariable Long postNo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postLikeService.createPostLike(postNo, userDetails.getUser());
    }
}
