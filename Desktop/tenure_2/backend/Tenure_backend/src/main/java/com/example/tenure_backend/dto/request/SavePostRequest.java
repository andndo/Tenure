package com.example.tenure_backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SavePostRequest {

    @NotBlank(message = "입력해주세요.")
    private String location;

    @NotBlank(message = "입력해주세요.")
    private String title;

    @NotBlank(message = "입력해주세요.")
    private String content;
}
