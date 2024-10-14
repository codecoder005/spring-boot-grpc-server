package com.popcorn.grpc.repository;

import com.popcorn.grpc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByNameLike(String likeName);
}
