package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.factory.EventIssueFactory;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;

import java.util.Optional;

import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.CONFLICT;

public class EventProcessorService {

	private final DeduplicationService deduplicationService;
	private final EventRepository eventRepository;
	private final EventIssueRepository eventIssueRepository;
	private final AccountSummaryService accountSummaryService;


	public EventProcessorService(DeduplicationService deduplicationService, EventRepository eventRepository,
								 EventIssueRepository eventIssueRepository, AccountSummaryService accountSummaryService) {
		this.deduplicationService = deduplicationService;
		this.eventRepository = eventRepository;
		this.eventIssueRepository = eventIssueRepository;
		this.accountSummaryService = accountSummaryService;
	}

	public void process(Event event) {
		Optional<Event> existingEventOptional = eventRepository.findByEventId(event.getEventId());

		if (!existingEventOptional.isPresent()) {
			this.accountSummaryService.aggregate(event);

			this.eventRepository.save(event);
		}

		Event existingEvent = existingEventOptional.get();

		DeduplicationResult result = this.deduplicationService.validateExistingEvent(existingEvent, event);

		this.createEventIssue(event, result);

		if (CONFLICT.equals(result)) {
			this.rollbackEvent(event);
		}
	}

	private void createEventIssue(Event event, DeduplicationResult result) {
		EventIssue eventIssue = EventIssueFactory.createEventIssue(event.getEventId(), result);

		this.saveIssue(eventIssue);
	}

	private void rollbackEvent(Event event) {
		this.eventRepository.deleteByEventId(event.getEventId());

		this.accountSummaryService.recalculateSummary(event);
	}

	private void saveIssue(EventIssue eventIssue) {
		this.eventIssueRepository.save(eventIssue);
	}
}
