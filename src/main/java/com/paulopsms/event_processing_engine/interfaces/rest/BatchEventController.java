package com.paulopsms.event_processing_engine.interfaces.rest;

import com.paulopsms.event_processing_engine.application.dto.EventRequest;
import com.paulopsms.event_processing_engine.application.mapper.RestEventMapper;
import com.paulopsms.event_processing_engine.application.usecase.ProcessBatchEventsUseCase;
import com.paulopsms.event_processing_engine.domain.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Void> createEvent(@RequestBody List<EventRequest> request,
											@RequestHeader(value = "X-Correlation-Id", required = false) String correlationId) {
		List<Event> events = request.stream()
				.map(RestEventMapper::toModel)
				.collect(Collectors.toList());

		processBatchEventsUseCase.processBatch(events);

		return ResponseEntity.accepted().build();
	}
}
