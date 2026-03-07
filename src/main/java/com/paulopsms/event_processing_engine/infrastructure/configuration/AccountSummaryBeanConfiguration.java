package com.paulopsms.event_processing_engine.infrastructure.configuration;

import com.paulopsms.event_processing_engine.application.usecase.GetAccountsSummaryUseCase;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.domain.service.GetAccountSummaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountSummaryBeanConfiguration {

	@Bean
	public GetAccountsSummaryUseCase createGetAccountsSummaryUseCase(GetAccountSummaryService getAccountSummaryService) {
		return new GetAccountsSummaryUseCase(getAccountSummaryService);
	}

	@Bean
	public GetAccountSummaryService createGetAccountSummaryService(AccountSummaryRepository accountSummaryRepository) {
		return new GetAccountSummaryService(accountSummaryRepository);
	}
}
