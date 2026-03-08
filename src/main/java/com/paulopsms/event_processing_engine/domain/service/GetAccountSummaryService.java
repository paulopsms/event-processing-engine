package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAccountSummaryService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AccountSummaryRepository accountSummaryRepository;

	public GetAccountSummaryService(AccountSummaryRepository accountSummaryRepository) {
		this.accountSummaryRepository = accountSummaryRepository;
	}

	public List<AccountSummary> findAllAccountSummaries() {
		List<AccountSummary> summaries = this.accountSummaryRepository.findAll();

		this.logger.info("Listing {} Summaries.", summaries.size());

		return summaries;
	}
}
