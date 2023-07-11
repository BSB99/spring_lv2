package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.*;
import com.sparta.login_blog.entity.Post;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.entity.UserRoleEnum;
import com.sparta.login_blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public PostListResponseDto getPosts() {
        List<PostResponseDto> postList = postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    public PostDetailResponseDto getPostById(Long id) {
        Post post = findPost(id);

        return new PostDetailResponseDto(post);
    }

    public ApiResponseDto deletePost(Long id, User user) {
        Post post = findPost(id);

        if(!vaildateUser(user,post)){
            throw new IllegalArgumentException("예상치 못한 오류");
        }

        postRepository.delete(post);

        return new ApiResponseDto("게시글 삭제 성공", 200);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = findPost(id);

        if(!vaildateUser(user,post)){
            throw new IllegalArgumentException("예상치 못한 오류");
        }

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);
    }

    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    private boolean vaildateUser(User user, Post post) {
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            return true;
        }
        else if (user.getUsername() != post.getUser().getUsername()) {
            throw new IllegalArgumentException("게시글을 작성한 유저가 아닙니다!");
        } else {
            return false;
        }
    }
}
