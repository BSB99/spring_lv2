package com.sparta.login_blog.repository;

import com.sparta.login_blog.entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
}
