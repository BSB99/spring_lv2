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
}
