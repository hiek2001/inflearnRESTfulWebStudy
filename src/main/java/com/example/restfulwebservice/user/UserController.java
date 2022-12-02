package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    // setter를 사용한 의존성 주입
    public UserController(UserDaoService service) {
        this.service = service;
    }

    // 전체 사용자 목록
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET  /users/1 or /users/10번 사용자 - 상세조회 => 서버에는 String형태로 전달 됨.
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") //반환시키고자 하는 값
                .buildAndExpand(savedUser.getId()) //새롭게 만들어진 id값을 추가함
                .toUri(); //uri 형태로 변환함
        return ResponseEntity.created(location).build(); //위에서 만든 uri를 build 함.
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> UpdateUser(@RequestBody User user) {
        User updateUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updateUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
