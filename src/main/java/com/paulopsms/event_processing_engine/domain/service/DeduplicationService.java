package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeduplicationService {

	private final Logger logger = LoggerFactory.getLogger(DeduplicationService.class);

	public DeduplicationResult validateExistingEvent(Event existingEvent, Event newEvent) {
		if (newEvent.equals(existingEvent)) {
			this.logger.warn("Duplication detected for event {}.", newEvent.getEventId());

			return DeduplicationResult.DUPLICATE;
		} else {
			this.logger.warn("Conflict detected for event {}.", newEvent.getEventId());

			return DeduplicationResult.CONFLICT;
		}
	}
}
