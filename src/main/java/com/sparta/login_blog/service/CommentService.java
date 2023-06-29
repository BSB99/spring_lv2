package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.CommentRequestDto;
import com.sparta.login_blog.dto.CommentResponseDto;
import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.Post;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    public CommentResponseDto createComment(CommentRequestDto request, String data, Long id) {
        User user = userService.getUser(data);
        Post post = postService.findPost(id);

        Comment comment = new Comment(request, user, post);

        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto updatePost(CommentRequestDto request, String data, Long postId, Long commentId) {
        User user = userService.getUser(data);
        postService.findPost(postId);
        Comment comment = findComment(commentId);

        if (comment.getPost().getId() != postId) {
            throw new IllegalArgumentException("게시글 번호를 확인해주세요");
        }

        if (comment.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("작성자가 아닙니다!");
        }

        comment.setContent(request.getContent());

        return new CommentResponseDto(comment);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
    }
}
