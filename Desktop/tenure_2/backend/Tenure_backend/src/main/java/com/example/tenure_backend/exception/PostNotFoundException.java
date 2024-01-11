package com.example.tenure_backend.exception;

import com.example.tenure_backend.exception.error.ErrorCode;
import com.example.tenure_backend.exception.handler.BaseException;

public class PostNotFoundException extends BaseException {

	public final static PostNotFoundException EXCEPTION = new PostNotFoundException();

	public PostNotFoundException() {
		super(ErrorCode.POST_NOT_FOUND);
	}

}
