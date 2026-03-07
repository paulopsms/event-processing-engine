package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;

public class ProcessEventUseCase {

	private final EventProcessorService eventProcessorService;

	public ProcessEventUseCase(EventProcessorService eventProcessorService) {
		this.eventProcessorService = eventProcessorService;
	}

	public void process(Event event) {
		this.eventProcessorService.process(event);
	}
}
