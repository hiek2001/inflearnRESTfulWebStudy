package com.example.restfulwebservice.exception;

import com.example.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // @ControllerAdvice : 모든 controller가 실행될때, bean이 실행됨. 모든 예외가 발생하면 해당 handler가 실행됨.

    // handleAllExceptions(Exception ex, WebRequest request)
    // Exception ex : 에러 객체
    // WebRequest request : 어디서 발생했는지에 대한 정보

    // @ExceptionHandler(Exception.class) : 모든 예외를 처리하는 것이기 때문에 Exception.class 사용

   @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // User가 없을 경우 404 Exception 호출함
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // ResponseEntityExceptionHandler (=부모 클래스)에서 가져온 클래스라서 Override 하기.
    // 잘못되어 있는 메소드라면 오류가 나타나기 때문.

    // MethodArgumentNotValidException ex : 발생하는 exception의 객체
    // HttpHeaders headers : Request의 header 값
    // HttpStatus status : 매개변수의 객체 값 state 값
    // WebRequest request : 어디서 발생했는지에 대한 정보

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
