package com.sparta.login_blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_likes")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_no", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
