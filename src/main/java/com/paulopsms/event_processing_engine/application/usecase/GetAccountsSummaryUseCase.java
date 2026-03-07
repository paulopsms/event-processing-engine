package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.service.GetAccountSummaryService;

import java.util.List;

public class GetAccountsSummaryUseCase {

	private final GetAccountSummaryService getAccountSummaryService;

	public GetAccountsSummaryUseCase(GetAccountSummaryService getAccountSummaryService) {
		this.getAccountSummaryService = getAccountSummaryService;
	}

	public List<AccountSummary> listSummaries() {
		return this.getAccountSummaryService.findAllAccountSummaries();
	}
}
