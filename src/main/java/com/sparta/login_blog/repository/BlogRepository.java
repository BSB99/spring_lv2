package com.sparta.login_blog.repository;

import com.sparta.login_blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
