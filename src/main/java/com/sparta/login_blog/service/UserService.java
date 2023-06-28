package com.sparta.login_blog.service;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.SignRequestDto;
import com.sparta.login_blog.entity.User;
import com.sparta.login_blog.jwt.JwtUtil;
import com.sparta.login_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    public ApiResponseDto signup(SignRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", 200);
    }

    public ApiResponseDto signIn(SignRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());

        jwtUtil.addJwtToCookie(token, res);
        return new ApiResponseDto("로그인 성공", 200);
    }

    public User getUser(String token) {
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        System.out.println(claims);
        User user = userRepository.findByUsername((String)claims.get("sub")).orElseThrow(() ->
                new NullPointerException("유저가 존재하지 않습니다")
        );

        return user;
    }
}
