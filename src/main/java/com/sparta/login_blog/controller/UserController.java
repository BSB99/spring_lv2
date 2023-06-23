package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.ApiResponseDto;
import com.sparta.login_blog.dto.SignRequestDto;
import com.sparta.login_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiResponseDto signUp(@RequestBody @Valid SignRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/signin")
    public ApiResponseDto signIn(@RequestBody @Valid SignRequestDto requestDto, HttpServletResponse res) {
        return userService.signIn(requestDto, res);
    }

}
