package com.paulopsms.event_processing_engine.interfaces.rest;

import com.paulopsms.event_processing_engine.application.dto.EventRequest;
import com.paulopsms.event_processing_engine.application.mapper.RestEventMapper;
import com.paulopsms.event_processing_engine.application.usecase.ProcessBatchEventsUseCase;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.EventMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("queue")
public class BatchEventController {

	private final ProcessBatchEventsUseCase processBatchEventsUseCase;

	public BatchEventController(ProcessBatchEventsUseCase processBatchEventsUseCase) {
		this.processBatchEventsUseCase = processBatchEventsUseCase;
	}

	@PostMapping("mock")
	public ResponseEntity<Void> createEvent(@RequestBody List<EventRequest> request) {
		List<Event> events = request.stream()
				.map(RestEventMapper::toModel)
				.collect(Collectors.toList());

		processBatchEventsUseCase.processBatch(events);

		return ResponseEntity.accepted().build();
	}
}
