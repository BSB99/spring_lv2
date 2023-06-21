package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.Blog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogResponseDto {
    private String title;
    private String content;

    public BlogResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.content = blog.getContent();
    }
}
