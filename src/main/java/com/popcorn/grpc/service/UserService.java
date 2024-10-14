package com.popcorn.grpc.service;

import com.popcorn.grpc.*;
import com.popcorn.grpc.entity.UserEntity;
import com.popcorn.grpc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class UserService extends UserServiceGrpc.UserServiceImplBase{
    private final UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest request, io.grpc.stub.StreamObserver<CreateUserResponse> responseObserver) {
        // We have mocked the employee data.
        // In real world this should be fetched from database or from some other source

        UserEntity draftUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        UserEntity createdUser = userRepository.saveAndFlush(draftUser);

        CreateUserResponse response = CreateUserResponse.newBuilder()
                .setUid(createdUser.getUid())
                .setName(request.getName())
                .setEmail(request.getEmail())
                .setCreatedAt(createdUser.getCreatedAt().toString())
                .setUpdatedAt(createdUser.getUpdatedAt() == null ? "" : createdUser.getUpdatedAt().toString())
                .build();


        // set the response object
        responseObserver.onNext(response);

        // mark process is completed
        responseObserver.onCompleted();
    }

    @Override
    public void getAllUsers(GetAllUsersRequest request, io.grpc.stub.StreamObserver<GetAllUsersResponse> responseObserver) {

        GetAllUsersResponse.Builder builder = GetAllUsersResponse.newBuilder();

        List<UserEntity> users = userRepository.findAll();
        for(UserEntity user : users){
            builder.addUsers(
                    UserResponse.newBuilder()
                            .setUid(user.getUid())
                            .setName(user.getName())
                            .setEmail(user.getEmail())
                            .setCreatedAt(user.getCreatedAt().toString())
                            .setUpdatedAt(user.getUpdatedAt() !=null ? user.getUpdatedAt().toString() : "")
                            .build()
            );
        }

        // set the response object
        responseObserver.onNext(builder.build());

        // mark process is completed
        responseObserver.onCompleted();
    }
}
