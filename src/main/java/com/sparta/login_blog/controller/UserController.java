package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.SignUpRequestDto;
import com.sparta.login_blog.dto.SigninRequestDto;
import com.sparta.login_blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입 API")
    public ResponseEntity<ApiResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        ApiResponseDto result = userService.signup(requestDto);

        return ResponseEntity.status(201).body(result);
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "로그인 API")
    public ApiResponseDto signin(@RequestBody SigninRequestDto requestDto, HttpServletResponse response) {
        userService.signin(requestDto, response);
        return new ApiResponseDto("로그인 완료", HttpStatus.OK.value());
    }
}
