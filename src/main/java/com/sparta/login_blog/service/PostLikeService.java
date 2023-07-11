package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.entity.Post;
import com.sparta.login_blog.entity.PostLike;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    public ApiResponseDto createPostLike(Long postNo, User user) {
        Post post = postService.findPost(postNo);
        PostLike postLike = new PostLike(post, user);
        Optional<PostLike> findPostLike= postLikeRepository.findByUserAndPost(user, post);

        if( findPostLike.isEmpty()) {
            postLikeRepository.save(postLike);
        } else {
            throw new IllegalArgumentException("댓글 하나에 좋아요는 한번만 할 수 있습니다!");
        }

        return new ApiResponseDto("댓글 좋아요 완료", 200);
    }
}
