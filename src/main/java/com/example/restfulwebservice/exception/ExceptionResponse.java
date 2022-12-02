package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse { // 일반화 예외 클래스
    private Date timestamp;
    private String message;
    private String details; // 예외 상세 정보

    // 예외가 발생하게 되면 Spring Boot Handler가 자동으로 반환할수 있게 하기기
}
