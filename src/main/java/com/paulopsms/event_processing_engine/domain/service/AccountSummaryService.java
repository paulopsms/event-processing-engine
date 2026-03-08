package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountSummaryService {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AccountSummaryRepository accountSummaryRepository;
	private final EventRepository eventRepository;

	public AccountSummaryService(AccountSummaryRepository accountSummaryRepository, EventRepository eventRepository) {
		this.accountSummaryRepository = accountSummaryRepository;
		this.eventRepository = eventRepository;
	}

	public void aggregate(Event event) {
		AccountSummary summary = this.accountSummaryRepository.findByAccountId(event.getAccountId())
				.orElse(new AccountSummary(event.getAccountId()));

		summary.apply(event);

		this.accountSummaryRepository.save(summary);
	}

	public void recalculateSummary(Event event) {
		this.logger.info("Recalculating Summary for account {}",  event.getAccountId());
		
		List<Event> events = this.eventRepository.findByAccountIdOrderByOcurredAt(event.getAccountId());

		this.logger.info("{} events found.",  events.size());

		AccountSummary summary = new AccountSummary(event.getAccountId());

		events.forEach(evnt -> this.recalculate(evnt, summary));

		this.accountSummaryRepository.save(summary);
	}

	private void recalculate(Event event, AccountSummary summary) {
		this.logger.info("Recalculating Summary for event: {}",  event)	;
		
		summary.apply(event);
	}
}
