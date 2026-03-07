package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;

import java.util.List;

public class GetAccountSummaryService {

	private final AccountSummaryRepository accountSummaryRepository;

	public GetAccountSummaryService(AccountSummaryRepository accountSummaryRepository) {
		this.accountSummaryRepository = accountSummaryRepository;
	}

	public List<AccountSummary> findAllAccountSummaries() {
		return this.accountSummaryRepository.findAll();
	}
}
