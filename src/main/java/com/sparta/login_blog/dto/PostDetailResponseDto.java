package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private String username;
    private int postLike;
    private List<CommentDetailResponseDto> commentList = new ArrayList<>();
    public PostDetailResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.postLike = post.getPostLikeList().size();
        this.createdAt = post.getCreatedAtAsString();
        this.modifiedAt = post.getModifiedAtAsString();
        for (Comment comment : post.getCommentList()) {
            commentList.add(new CommentDetailResponseDto(comment));
        }
    }
}
