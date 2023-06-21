package com.sparta.login_blog.controller;

import com.sparta.login_blog.dto.SignRequestDto;
import com.sparta.login_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid SignRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공";
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody @Valid SignRequestDto requestDto, HttpServletResponse res) {
        String token = userService.signIn(requestDto, res);
        return token;
    }

}
