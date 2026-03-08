package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessEventUseCase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final EventProcessorService eventProcessorService;

	public ProcessEventUseCase(EventProcessorService eventProcessorService) {
		this.eventProcessorService = eventProcessorService;
	}

	public void process(Event event) {
		this.logger.info("Processing Single Event.");

		this.eventProcessorService.process(event);
	}
}
