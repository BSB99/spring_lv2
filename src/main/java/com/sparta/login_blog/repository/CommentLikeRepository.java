package com.sparta.login_blog.repository;

import com.sparta.login_blog.entity.Comment;
import com.sparta.login_blog.entity.CommentLike;
import com.sparta.login_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
