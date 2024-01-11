package com.example.tenure_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter @Builder
public class MypageResponse {

    private final String userId;
    private final String nickname;
    private final String job;
    private final List<PostElement> postElementList;

    @Getter @Builder
    public static class PostElement {
        private final Long id;
        private final String location;
        private final String title;
        private final String content;
        private final LocalDate date;
    }
}

