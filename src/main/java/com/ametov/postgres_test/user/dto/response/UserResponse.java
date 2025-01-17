package com.ametov.postgres_test.user.dto.response;

import com.ametov.postgres_test.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserResponse{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserResponse of(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
