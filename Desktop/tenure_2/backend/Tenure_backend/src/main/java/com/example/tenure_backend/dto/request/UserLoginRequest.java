package com.example.tenure_backend.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @NotNull(message = "입력해주세요.")
    private String userId;

    @NotNull(message = "입력해주세요.")
    private String password;

}