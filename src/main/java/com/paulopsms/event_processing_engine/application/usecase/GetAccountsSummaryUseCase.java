package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.service.AccountSummaryService;

import java.util.List;

public class GetAccountsSummaryUseCase {

	private final AccountSummaryService accountSummaryService;

	public GetAccountsSummaryUseCase(AccountSummaryService accountSummaryService) {
		this.accountSummaryService = accountSummaryService;
	}

	public List<AccountSummary> listSummaries() {
		return this.accountSummaryService.findAllAccountSummaries();
	}
}
