package com.paulopsms.event_processing_engine.shared.exception;

public class BusinessRuntimeException extends RuntimeException {
	public BusinessRuntimeException(String message) {
		super(message);
	}

	public BusinessRuntimeException(Throwable cause) {
		super(cause);
	}

	public BusinessRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
