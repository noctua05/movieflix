package com.movieflix.mapper;

import com.movieflix.entity.UserEntity;
import com.movieflix.request.UserRequest;
import com.movieflix.response.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserEntity toUserEntity(UserRequest request){
        return UserEntity.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(UserEntity entity){
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
