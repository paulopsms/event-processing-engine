package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;

import java.util.Optional;

public class DeduplicationService {

	private final EventRepository eventRepository;
	private final EventIssueRepository eventIssueRepository;

	public DeduplicationService(EventRepository eventRepository, EventIssueRepository eventIssueRepository) {
		this.eventRepository = eventRepository;
		this.eventIssueRepository = eventIssueRepository;
	}

	public DeduplicationResult checkDeduplication(Event event) {
		Optional<Event> optionalEventFound = eventRepository.findByEventId(event.getEventId());

		return optionalEventFound
				.map(found -> this.validateExistingEvent(event, found))
				.orElse(DeduplicationResult.OK);
	}


	private DeduplicationResult validateExistingEvent(Event event, Event eventFound) {
		if (eventFound.equals(event)) {
			return DeduplicationResult.DUPLICATE;
		} else {
			return DeduplicationResult.CONFLICT;
		}
	}


}
