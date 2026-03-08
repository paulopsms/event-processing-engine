package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessBatchEventsUseCase {

	private final EventProcessorService eventProcessorService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ProcessBatchEventsUseCase(EventProcessorService eventProcessorService) {
		this.eventProcessorService = eventProcessorService;
	}

	public void processBatch(List<Event> events) {
		this.logger.info("Processing Batch Events.");

		List<Event> orderedEvents = events.stream()
				.sorted(this.orderByOccuredAtAndEventId())
				.collect(Collectors.toList());

		for (Event event : orderedEvents) {
			this.eventProcessorService.process(event);
		}
	}

	private Comparator<Event> orderByOccuredAtAndEventId() {
		return Comparator.comparing(Event::getOccurredAt).thenComparing(Event::getEventId);
	}
}
