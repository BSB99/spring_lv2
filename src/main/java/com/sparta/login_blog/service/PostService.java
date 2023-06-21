package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.ApiResponseDto;
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

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    public ApiResponseDto deletePost(Long id) {
        Post post = findPost(id);

        postRepository.delete(post);

        return new ApiResponseDto("게시글 삭제 성공", 200);
    }

    private Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
