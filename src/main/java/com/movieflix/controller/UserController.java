package com.movieflix.controller;

import com.movieflix.Exceptions.UsernameOrPasswordInvalidException;
import com.movieflix.Service.UserService;
import com.movieflix.config.TokenService;
import com.movieflix.entity.UserEntity;
import com.movieflix.mapper.UserMapper;
import com.movieflix.request.UserRequest;
import com.movieflix.response.LoginResponse;
import com.movieflix.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUSer(@RequestBody UserRequest request){
        UserEntity savedUser = userService.save(UserMapper.toUserEntity(request));
        return ResponseEntity.ok(UserMapper.toUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest request){

        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            UserEntity user = (UserEntity) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));

        }catch (AuthenticationException e){
            throw new UsernameOrPasswordInvalidException("Usuário ou Senha inválido.");
        }
    }
}
