package com.example.tenure_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    @NotBlank(message = "입력해주세요.")
    private String userId;

    @NotBlank(message = "입력해주세요.")
    private String nickname;

    @NotBlank(message = "입력해주세요.")
    private String password;

    @NotBlank(message = "입력해주세요.")
    private String job;

}