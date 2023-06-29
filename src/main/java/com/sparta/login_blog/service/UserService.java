package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.SignUpRequestDto;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.entity.UserRoleEnum;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    public ApiResponseDto signup(SignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        if (requestDto.isAdmin()) {
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, role);
        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", 200);
    }

    public User getUser(String token) {
        Claims claims = jwtUtil.getUserInfoFromToken(token);

        User user = userRepository.findByUsername((String)claims.get("sub")).orElseThrow(() ->
                new NullPointerException("유저가 존재하지 않습니다")
        );

        return user;
    }
}
