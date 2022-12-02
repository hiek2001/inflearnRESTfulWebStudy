package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status Code
// 2XX -> OK
// 4XX -> Client의 문제
// 5XX -> Server의 문제
// 리소스 존재 X
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException { //RuntimeException : 실행시 발생하는 오류
    public UserNotFoundException(String message) {
        super(message); //부모 클래스에서 발생하는 message를 출력함.
    }
}
// @ResponseStatus(HttpStatus.NOT_FOUND) 이걸 쓰면 상태코드 5XX이 아니라 4XX을 반환