package com.paulopsms.event_processing_engine.infrastructure.configuration;

import com.paulopsms.event_processing_engine.application.usecase.GetEventIssuesUseCase;
import com.paulopsms.event_processing_engine.application.usecase.ProcessBatchEventsUseCase;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;
import com.paulopsms.event_processing_engine.domain.service.GetEventIssueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventIssueBeanConfiguration {

	@Bean
	public GetEventIssuesUseCase createGetEventIssuesUseCase(GetEventIssueService getEventIssueService) {
		return new GetEventIssuesUseCase(getEventIssueService);
	}

	@Bean
	public GetEventIssueService createGetEventIssueService(EventIssueRepository eventIssueRepository) {
		return new GetEventIssueService(eventIssueRepository);
	}
}
