package com.paulopsms.event_processing_engine.infrastructure.web;

import com.paulopsms.event_processing_engine.shared.exception.BusinessRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvicer {

	@ExceptionHandler(BusinessRuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> businessExceptionHandler(BusinessRuntimeException ex) {
		ex.printStackTrace();

		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> generalException(Exception ex) {
		ex.printStackTrace();

		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
}
