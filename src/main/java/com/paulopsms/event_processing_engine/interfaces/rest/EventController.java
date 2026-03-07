package com.paulopsms.event_processing_engine.interfaces.rest;

import com.paulopsms.event_processing_engine.application.dto.EventRequest;
import com.paulopsms.event_processing_engine.application.mapper.RestEventMapper;
import com.paulopsms.event_processing_engine.application.usecase.ProcessEventUseCase;
import com.paulopsms.event_processing_engine.domain.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventController {

	private final ProcessEventUseCase processEventUseCase;

	public EventController(ProcessEventUseCase processEventUseCase) {
		this.processEventUseCase = processEventUseCase;
	}

	@PostMapping("ingest")
	public ResponseEntity<Void> createEvent(@RequestBody EventRequest request) {
		Event event = RestEventMapper.toModel(request);

		processEventUseCase.process(event);

		return ResponseEntity.accepted().build();
	}
}
