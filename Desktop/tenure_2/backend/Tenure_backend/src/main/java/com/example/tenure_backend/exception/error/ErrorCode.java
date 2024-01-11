package com.example.tenure_backend.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	USER_NOT_FOUND(404,"유저를 찾을 수 없습니다."),
	POST_NOT_FOUND(404,"게시글을 찾을 수 없습니다."),
	NOT_MATCHES_PASSWORD(409, "비밀번호가 일치하지 않습니다.");

	private final int status;
	private final String message;

}
