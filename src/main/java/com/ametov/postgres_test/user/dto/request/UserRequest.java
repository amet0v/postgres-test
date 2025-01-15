package com.ametov.postgres_test.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
}
