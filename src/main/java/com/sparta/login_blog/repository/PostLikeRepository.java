package com.sparta.login_blog.repository;

import com.sparta.login_blog.entity.Post;
import com.sparta.login_blog.entity.PostLike;
import com.sparta.login_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);
}
