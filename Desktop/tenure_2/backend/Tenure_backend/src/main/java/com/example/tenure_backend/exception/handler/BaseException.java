package com.example.tenure_backend.exception.handler;

import com.example.tenure_backend.exception.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

	private final ErrorCode errorCode;

}
