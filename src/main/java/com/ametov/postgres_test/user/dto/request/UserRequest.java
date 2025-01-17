package com.ametov.postgres_test.user.dto.request;

import com.ametov.postgres_test.user.exception.BadRequestException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void validate() throws BadRequestException {
        if (email == null || email.isBlank()) throw new BadRequestException();
        if (password == null || password.isBlank()) throw new BadRequestException();
    }
}
