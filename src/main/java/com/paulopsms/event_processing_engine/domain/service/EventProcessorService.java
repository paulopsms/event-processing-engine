package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.factory.EventIssueFactory;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;

import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.CONFLICT;
import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.OK;

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

		DeduplicationResult result = deduplicationService.checkDeduplication(event);

		if (!OK.equals(result)) {
			EventIssue eventIssue = EventIssueFactory.createEventIssue(event.getEventId(), result);

			this.saveIssue(eventIssue);

			if (CONFLICT.equals(result)) {
				this.eventRepository.deleteByEventId(event.getEventId());

				this.accountSummaryService.recalculateSummary(event);
			}
		} else {
			this.eventRepository.save(event);

			this.accountSummaryService.aggregate(event);
		}
	}

	private void saveIssue(EventIssue eventIssue) {
		this.eventIssueRepository.save(eventIssue);
	}
}
