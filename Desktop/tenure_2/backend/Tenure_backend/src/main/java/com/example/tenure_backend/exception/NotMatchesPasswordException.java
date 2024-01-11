package com.example.tenure_backend.exception;

import com.example.tenure_backend.exception.error.ErrorCode;
import com.example.tenure_backend.exception.handler.BaseException;

public class NotMatchesPasswordException extends BaseException {

	public final static NotMatchesPasswordException EXCEPTION = new NotMatchesPasswordException();

	public NotMatchesPasswordException() {
		super(ErrorCode.NOT_MATCHES_PASSWORD);
	}
}