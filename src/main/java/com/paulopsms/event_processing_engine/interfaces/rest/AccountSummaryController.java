package com.paulopsms.event_processing_engine.interfaces.rest;

import com.paulopsms.event_processing_engine.application.dto.AccountSummaryResponse;
import com.paulopsms.event_processing_engine.application.mapper.RestAccountSummaryMapper;
import com.paulopsms.event_processing_engine.application.usecase.GetAccountsSummaryUseCase;
import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountSummaryController {

	private final GetAccountsSummaryUseCase getAccountsSummaryUseCase;

	public AccountSummaryController(GetAccountsSummaryUseCase getAccountsSummaryUseCase) {
		this.getAccountsSummaryUseCase = getAccountsSummaryUseCase;
	}

	@GetMapping("summary")
	public ResponseEntity<List<AccountSummaryResponse>> listAllSummaries() {
		List<AccountSummary> accountSummaries = this.getAccountsSummaryUseCase.listSummaries();

		List<AccountSummaryResponse> summaries = accountSummaries
				.stream()
				.map(RestAccountSummaryMapper::toResponse)
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(summaries);
	}
}
