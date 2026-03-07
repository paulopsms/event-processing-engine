package com.paulopsms.event_processing_engine.application.mapper;

import com.paulopsms.event_processing_engine.application.dto.AccountSummaryResponse;
import com.paulopsms.event_processing_engine.domain.model.AccountSummary;

public class RestAccountSummaryMapper {

	public static AccountSummaryResponse toResponse(AccountSummary summary) {

		AccountSummaryResponse response = new AccountSummaryResponse();

		response.setAccountId(summary.getAccountId());
		response.setBalance(summary.getBalance());
		response.setTotalCredits(summary.getTotalCredits());
		response.setTotalDebits(summary.getTotalDebits());
		response.setValidEvents(summary.getValidEvents());
		response.setConflictEvents(summary.getConflictEvents());
		response.setDuplicateEvents(summary.getDuplicateEvents());

		return response;
	}
}
