package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.CommentReplyResponseDto;
import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.CommentReply;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.repository.CommentReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReplyService {
    private final CommentReplyRepository commentReplyRepository;
    private final CommentService commentService;
    public CommentReplyResponseDto createCommentReply(String content, Long commentNo, User user) {
        Comment comment = commentService.findComment(commentNo);

        CommentReply reply = new CommentReply(content, comment, user);

        commentReplyRepository.save(reply);

        return new CommentReplyResponseDto(reply);
    }
}
