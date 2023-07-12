package com.sparta.login_blog.dto;

import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.CommentReply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentDetailResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
    private int likeCount;
    private List<CommentReplyResponseDto> commentReplyList = new ArrayList<CommentReplyResponseDto>();

    public CommentDetailResponseDto (Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.likeCount = comment.getCommentLikeList().size();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        for (CommentReply reply : comment.getCommentReplyList()) {
            commentReplyList.add(new CommentReplyResponseDto(reply));
        }
    }
}
