package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private String username;
    private int likeCount;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.likeCount = comment.getCommentLikeList().size();
        this.createdAt = comment.getCreatedAtAsString();
        this.modifiedAt = comment.getModifiedAtAsString();
    }
}
