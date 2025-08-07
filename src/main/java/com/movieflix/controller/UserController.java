package com.movieflix.controller;

import com.movieflix.Service.UserService;
import com.movieflix.entity.UserEntity;
import com.movieflix.mapper.UserMapper;
import com.movieflix.request.UserRequest;
import com.movieflix.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUSer(@RequestBody UserRequest request){
        UserEntity savedUser = userService.save(UserMapper.toUserEntity(request));
        return ResponseEntity.ok(UserMapper.toUserResponse(savedUser));
    }
}
