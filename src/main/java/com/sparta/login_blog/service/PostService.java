package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.PostRequestDto;
import com.sparta.login_blog.dto.PostResponseDto;
import com.sparta.login_blog.entity.Post;
import com.sparta.login_blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        postRepository.save(post);

        PostResponseDto responseDto = new PostResponseDto(post);

        return responseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }
}
