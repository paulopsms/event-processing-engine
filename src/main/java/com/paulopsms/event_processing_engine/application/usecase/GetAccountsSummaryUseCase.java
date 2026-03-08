package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.service.GetAccountSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAccountsSummaryUseCase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final GetAccountSummaryService getAccountSummaryService;

	public GetAccountsSummaryUseCase(GetAccountSummaryService getAccountSummaryService) {
		this.getAccountSummaryService = getAccountSummaryService;
	}

	public List<AccountSummary> listSummaries() {
		this.logger.info("Listing Account Summaries.");

		return this.getAccountSummaryService.findAllAccountSummaries();
	}
}
