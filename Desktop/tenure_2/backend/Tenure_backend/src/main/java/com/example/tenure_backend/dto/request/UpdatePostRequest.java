package com.example.tenure_backend.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdatePostRequest {

    @NotBlank(message = "입력해주세요.")
    private String title;

    @NotBlank(message = "입력해주세요.")
    private String content;

}
