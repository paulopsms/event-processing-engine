package com.paulopsms.event_processing_engine.shared.exception;

public class EventException extends RuntimeException {
	public EventException(String message) {
		super(message);
	}

	public EventException(Throwable cause) {
		super(cause);
	}

	public EventException(String message, Throwable cause) {
		super(message, cause);
	}
}
