package com.exercise.exer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//RuntimeException 실행 예외 처리
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DataNotFoundException 생성자 생성
	public DataNotFoundException(String message) {
		super(message);
	}
}