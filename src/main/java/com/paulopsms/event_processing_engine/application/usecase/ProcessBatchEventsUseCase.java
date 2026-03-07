package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;

import java.util.List;

public class ProcessBatchEventsUseCase {

	private final EventProcessorService eventProcessorService;

	public ProcessBatchEventsUseCase(EventProcessorService eventProcessorService) {
		this.eventProcessorService = eventProcessorService;
	}

	public void processBatch(List<Event> events) {
		//TODO To be implemented
	}
}
