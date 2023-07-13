package com.sparta.login_blog.aop;

import com.sparta.login_blog.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Aspect
@Component
@Slf4j(topic = "loginException")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Pointcut("execution(* com.sparta.login_blog.controller.UserController.*(..))")
    public void loginException() {}

    @AfterThrowing(pointcut = "loginException()", throwing = "ex")
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponseDto> handleException(IllegalArgumentException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(restApiException, HttpStatus.BAD_REQUEST);
    }
}


