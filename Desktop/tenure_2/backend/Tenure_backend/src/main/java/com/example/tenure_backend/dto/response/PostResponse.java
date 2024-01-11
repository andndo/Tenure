package com.example.tenure_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponse {

    private final List<PostElement> postElementList;

    @Getter @Builder
    public static class PostElement {
        private final String memberId;
        private final String nickname;
        private final String location;

        private final Long id;
        private final String title;
        private final String content;
        private final LocalDate date;
    }
}

