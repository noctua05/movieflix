package com.movieflix.Service;

import com.movieflix.entity.UserEntity;
import com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity save(UserEntity entity){
       return userRepository.save(entity);
    }
}
