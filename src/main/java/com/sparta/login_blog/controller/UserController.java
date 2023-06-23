package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.SignRequestDto;
import com.sparta.login_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signUp(@RequestBody @Valid SignRequestDto requestDto) {
        ApiResponseDto result = userService.signup(requestDto);

        return ResponseEntity.status(201).body(result);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto> signIn(@RequestBody @Valid SignRequestDto requestDto, HttpServletResponse res) {
        ApiResponseDto result = userService.signIn(requestDto, res);

        return ResponseEntity.status(200).body(result);
    }

}
