package com.springboot.farm.springbootpractice.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.farm.springbootpractice.handler.exception.CustomValidationApiException;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return ResponseEntity.badRequest().body(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()));
	}
}