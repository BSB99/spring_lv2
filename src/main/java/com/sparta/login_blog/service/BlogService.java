package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.BlogRequestDto;
import com.sparta.login_blog.dto.BlogResponseDto;
import com.sparta.login_blog.entity.Blog;
import com.sparta.login_blog.repository.BlogRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);

        blogRepository.save(blog);

        BlogResponseDto responseDto = new BlogResponseDto(blog);

        return responseDto;
    }
}
