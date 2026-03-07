package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.factory.EventIssueFactory;
import com.paulopsms.event_processing_engine.domain.model.event.Event;
import com.paulopsms.event_processing_engine.domain.model.event.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;

public class EventProcessorService {

	private final DeduplicationService deduplicationService;
	private final EventRepository eventRepository;
	private final EventIssueRepository eventIssueRepository;


	public EventProcessorService(DeduplicationService deduplicationService, EventRepository eventRepository, EventIssueRepository eventIssueRepository) {
		this.deduplicationService = deduplicationService;
		this.eventRepository = eventRepository;
		this.eventIssueRepository = eventIssueRepository;
	}

	public void process(Event event) {

		DeduplicationResult deduplicationResult = deduplicationService.checkDeduplication(event);

		if (!DeduplicationResult.OK.equals(deduplicationResult)) {
			EventIssue duplicateEventIssue = EventIssueFactory.createEventIssue(event.getEventId(), deduplicationResult);

			this.saveIssue(duplicateEventIssue);
		} else {
			this.eventRepository.save(event);

			// TODO aggregate?
		}
	}

	private void saveIssue(EventIssue eventIssue) {
		this.eventIssueRepository.save(eventIssue);
	}
}
