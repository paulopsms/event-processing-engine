package com.paulopsms.event_processing_engine.infrastructure.configuration;

import com.paulopsms.event_processing_engine.application.usecase.ProcessEventUseCase;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import com.paulopsms.event_processing_engine.domain.service.AccountSummaryService;
import com.paulopsms.event_processing_engine.domain.service.DeduplicationService;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;
import com.paulopsms.event_processing_engine.infrastructure.adapter.AccountSummaryAdapter;
import com.paulopsms.event_processing_engine.infrastructure.adapter.EventAdapter;
import com.paulopsms.event_processing_engine.infrastructure.adapter.EventIssueAdapter;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.AccountSummaryMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.EventIssueMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.EventMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.AccountSummaryJpaRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventIssueJpaRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBeanConfiguration {

	@Bean
	public EventRepository createEventRepository(EventJpaRepository eventJpaRepository, EventMapper eventMapper) {
		return new EventAdapter(eventJpaRepository, eventMapper);
	}

	@Bean
	public EventIssueRepository createEventIssueRepository(EventIssueJpaRepository eventIssueJpaRepository, EventIssueMapper eventMapper) {
		return new EventIssueAdapter(eventIssueJpaRepository, eventMapper);
	}

	@Bean
	public AccountSummaryRepository createAccountSummaryRepository(AccountSummaryJpaRepository accountSummaryJpaRepository,
																   AccountSummaryMapper mapper) {
		return new AccountSummaryAdapter(accountSummaryJpaRepository, mapper);
	}

	@Bean
	public AccountSummaryService createAccountSummaryService(AccountSummaryRepository accountSummaryRepository,
															 EventRepository eventRepository) {
		return new AccountSummaryService(accountSummaryRepository, eventRepository);
	}

	@Bean
	public DeduplicationService createDeduplicationService(EventRepository eventRepository) {
		return new DeduplicationService(eventRepository);
	}

	@Bean
	public EventProcessorService createEventProcessorService(DeduplicationService deduplicationService,
															 EventRepository eventRepository,
															 EventIssueRepository eventIssueRepository,
															 AccountSummaryService accountSummaryService) {
		return new EventProcessorService(deduplicationService, eventRepository, eventIssueRepository, accountSummaryService);
	}

	@Bean
	public ProcessEventUseCase createProcessEventUseCase(EventProcessorService eventProcessorService) {
		return new ProcessEventUseCase(eventProcessorService);
	}

}
