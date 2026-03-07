package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.service.AccountSummaryService;

import java.util.List;

public class GetEventIssuesUseCase {

	public final AccountSummaryService accountSummaryService;

	public GetEventIssuesUseCase(AccountSummaryService accountSummaryService) {
		this.accountSummaryService = accountSummaryService;
	}

	public List<AccountSummary> listAllSummaries() {
		return this.accountSummaryService.findAllAccountSummaries();
	}
}
