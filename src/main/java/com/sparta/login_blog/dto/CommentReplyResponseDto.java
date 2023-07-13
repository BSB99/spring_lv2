package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.CommentReply;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentReplyResponseDto {
    private Long id;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private String username;

    public CommentReplyResponseDto (CommentReply reply){
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAtAsString();
        this.modifiedAt = reply.getModifiedAtAsString();
        this.username = reply.getUser().getUsername();
    }
}
