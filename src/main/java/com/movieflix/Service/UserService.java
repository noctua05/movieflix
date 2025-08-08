package com.movieflix.Service;

import com.movieflix.entity.UserEntity;
import com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserEntity save(UserEntity entity){
       String password = entity.getPassword();
       entity.setPassword(passwordEncoder.encode(password));
        return userRepository.save(entity);
    }
}
