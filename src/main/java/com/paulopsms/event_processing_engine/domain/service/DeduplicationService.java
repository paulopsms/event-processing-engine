package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.model.Event;

public class DeduplicationService {

	public DeduplicationResult validateExistingEvent(Event existingEvent, Event newEvent) {
		if (newEvent.equals(existingEvent)) {
			return DeduplicationResult.DUPLICATE;
		} else {
			return DeduplicationResult.CONFLICT;
		}
	}
}
