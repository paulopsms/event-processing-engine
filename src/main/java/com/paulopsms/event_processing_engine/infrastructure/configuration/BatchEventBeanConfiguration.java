package com.paulopsms.event_processing_engine.infrastructure.configuration;

import com.paulopsms.event_processing_engine.application.usecase.ProcessBatchEventsUseCase;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchEventBeanConfiguration {

	@Bean
	public ProcessBatchEventsUseCase createProcessBatchEventsUseCase(EventProcessorService eventProcessorService) {
		return new ProcessBatchEventsUseCase(eventProcessorService);
	}
}
