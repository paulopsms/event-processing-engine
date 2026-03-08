package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.service.EventProcessorService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProcessBatchEventsUseCase {

	private final EventProcessorService eventProcessorService;

	public ProcessBatchEventsUseCase(EventProcessorService eventProcessorService) {
		this.eventProcessorService = eventProcessorService;
	}

	public void processBatch(List<Event> events) {
//		if (correlationId == null || correlationId.isEmpty()) {
//			correlationId = UUID.randomUUID().toString();
//		}

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
