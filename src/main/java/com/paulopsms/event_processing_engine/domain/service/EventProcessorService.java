package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.factory.EventIssueFactory;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.CONFLICT;

public class EventProcessorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		this.logger.info("Processing event: {}",  event);
		Optional<Event> existingEventOptional = eventRepository.findByEventId(event.getEventId());

		if (!existingEventOptional.isPresent()) {
			this.logger.info("The event {} is valid.",  event.getEventId());

			this.accountSummaryService.aggregate(event);

			this.eventRepository.save(event);
		}
		Event existingEvent = existingEventOptional.get();

		this.logger.info("Validating event deduplication: {}",  existingEvent);

		DeduplicationResult result = this.deduplicationService.validateExistingEvent(existingEvent, event);

		this.createEventIssue(event, result);

		if (CONFLICT.equals(result)) {
			this.logger.info("Conflict detected. Executing rollback for event {}. ",  existingEvent);

			this.rollbackEvent(event);
		}
	}

	private void createEventIssue(Event event, DeduplicationResult result) {
		this.logger.info("Creating event issue: eventId={}, DeduplicationResult={}",  event.getEventId(), result);

		EventIssue eventIssue = EventIssueFactory.createEventIssue(event.getEventId(), result);

		this.saveIssue(eventIssue);
	}

	private void rollbackEvent(Event event) {
		this.logger.info("Removing event: {}",  event.getEventId());

		this.eventRepository.deleteByEventId(event.getEventId());

		this.accountSummaryService.recalculateSummary(event);
	}

	private void saveIssue(EventIssue eventIssue) {
		this.eventIssueRepository.save(eventIssue);
	}
}
