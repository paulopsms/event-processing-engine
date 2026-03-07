package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;

import java.util.List;

public class AccountSummaryService {

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
		List<Event> events = this.eventRepository.findByAccountIdOrderByOcurredAt(event.getAccountId());

		AccountSummary summary = new AccountSummary(event.getAccountId());

		events.forEach(summary::apply);

		this.accountSummaryRepository.save(summary);
	}
}
