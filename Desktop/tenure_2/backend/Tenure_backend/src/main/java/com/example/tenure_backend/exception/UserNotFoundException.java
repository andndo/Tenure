package com.example.tenure_backend.exception;

import com.example.tenure_backend.exception.error.ErrorCode;
import com.example.tenure_backend.exception.handler.BaseException;

public class UserNotFoundException extends BaseException {

	public final static UserNotFoundException EXCEPTION = new UserNotFoundException();

	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

}
