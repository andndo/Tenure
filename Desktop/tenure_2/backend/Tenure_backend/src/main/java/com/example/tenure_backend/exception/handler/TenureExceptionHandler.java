package com.example.tenure_backend.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TenureExceptionHandler {

	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<ErrorResponse> handlerDcsException(final BaseException e) {
		log.error("[NewTrashException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> HandleNullPointerException(final NullPointerException e) {
		log.error("[NullPointerException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> HandleValidationException(final MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append(fieldError.getField()).append("(을)를 ");
			builder.append(fieldError.getDefaultMessage()).append("   ");
		}

		log.error("[HandleValidationException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, builder.toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("[HttpRequestMethodNotSupportedException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(405, e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> NoHandlerFoundException(NoHandlerFoundException e){
		log.error("[NoHandlerFoundException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(404, "요청한 페이지를 찾을 수 없습니다. { URL : " +  e.getRequestURL() + " }"), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<ErrorResponse> MissingRequestHeaderException(Exception e) {
		log.error("[MissingRequestHeaderException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ErrorResponse> MissingServletRequestParameterException(Exception e) {
		log.error("[MissingServletRequestParameterException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	protected ResponseEntity<ErrorResponse> MissingServletRequestPartException(Exception e) {
		log.error("[MissingServletRequestPartException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ErrorResponse> IllegalArgumentException(Exception e) {
		log.error("[IllegalArgumentException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ErrorResponse> ConstraintViolationException(Exception e) {
		log.error("[ConstraintViolationException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorResponse> HttpMessageNotReadableException(Exception e) {
		log.error("[HttpMessageNotReadableException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("[exception] : " + e.getMessage(), e);
		return new ResponseEntity<>(new ErrorResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
