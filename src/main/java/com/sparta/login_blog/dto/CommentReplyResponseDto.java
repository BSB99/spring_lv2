package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.CommentReply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentReplyResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;

    public CommentReplyResponseDto (CommentReply reply){
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();
        this.username = reply.getUser().getUsername();
    }
}
