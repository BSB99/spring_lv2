package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.CommentReplyResponseDto;
import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.CommentReply;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.entity.UserRoleEnum;
import com.sparta.login_blog.repository.CommentReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CommentReplyResponseDto updateCommentReply(CommentRequestDto request, User user, Long commentNo) {
        CommentReply reply = findCommentReply(commentNo);

        if(!commentReplyVerificate(reply,user)) {
            throw new IllegalArgumentException("예상치 못한 에러 발생");
        }

        reply.setContent(request.getContent());

        return new CommentReplyResponseDto(reply);
    }

    public ApiResponseDto deleteCommentReply(Long commentNo, User user) {
        CommentReply reply = findCommentReply(commentNo);

        if(!commentReplyVerificate(reply,user)) {
            throw new IllegalArgumentException("예상치 못한 에러 발생");
        }

        commentReplyRepository.delete(reply);

        return new ApiResponseDto("답글 삭제 완료", 200);
    }

    private CommentReply findCommentReply(Long commentNo) {
        return commentReplyRepository.findById(commentNo).orElseThrow(() -> {
            throw new IllegalArgumentException("답글이 존재하지 않습니다.");
        });
    }

    private Boolean commentReplyVerificate(CommentReply reply, User user) {
        if(user.getRole().equals(UserRoleEnum.ADMIN)) {
            return true;
        }else if (reply.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("작성자가 아닙니다!");
        }else {
            return true;
        }
    }
}
