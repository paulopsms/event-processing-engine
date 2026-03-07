package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;

public class AccountSummaryService {

	private final AccountSummaryRepository accountSummaryRepository;

	public AccountSummaryService(AccountSummaryRepository accountSummaryRepository) {
		this.accountSummaryRepository = accountSummaryRepository;
	}

	public void aggregate(Event event) {
		AccountSummary summary = this.accountSummaryRepository.findByAccountId(event.getAccountId())
				.orElse(new AccountSummary(event.getAccountId()));

		summary.apply(event);

		this.accountSummaryRepository.save(summary);
	}
}
