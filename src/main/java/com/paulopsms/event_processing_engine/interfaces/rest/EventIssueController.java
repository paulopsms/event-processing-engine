package com.paulopsms.event_processing_engine.interfaces.rest;

import com.paulopsms.event_processing_engine.application.dto.EventIssueResponse;
import com.paulopsms.event_processing_engine.application.mapper.RestEventIssueMapper;
import com.paulopsms.event_processing_engine.application.usecase.GetEventIssuesUseCase;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("events")
public class EventIssueController {

	private final GetEventIssuesUseCase getEventIssuesUseCase;

	public EventIssueController(GetEventIssuesUseCase getEventIssuesUseCase) {
		this.getEventIssuesUseCase = getEventIssuesUseCase;
	}

	@GetMapping("issues")
	public ResponseEntity<List<EventIssueResponse>> listEventIssues() {
		List<EventIssue> issues = this.getEventIssuesUseCase.listAllSummaries();

		List<EventIssueResponse> issuesList = issues.stream()
				.map(RestEventIssueMapper::toResponse)
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(issuesList);
	}


}
