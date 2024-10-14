package com.popcorn.grpc.mapper;

import com.popcorn.grpc.UserResponse;
import com.popcorn.grpc.entity.UserEntity;

public class UserMapper {
    public static UserEntity map(UserResponse response) {
        return UserEntity.builder()
                .uid(response.getUid())
                .name(response.getName())
                .email(response.getEmail())
                .build();
    }

    public static UserResponse map(UserEntity entity) {
        return UserResponse.newBuilder()
                .setUid(entity.getUid())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }
}
