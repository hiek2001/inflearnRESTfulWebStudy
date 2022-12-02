package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;

//    = @AllArgsConstructor
//    public HelloWorldBean(String message) {
//        this.message = message;
//    }

    // @NoArgsConstructor -> default 생성자가 같이 생성됨
}
