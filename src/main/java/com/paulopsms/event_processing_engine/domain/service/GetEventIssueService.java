package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;

import java.util.List;

public class GetEventIssueService {

	private final EventIssueRepository eventIssueRepository;

	public GetEventIssueService(EventIssueRepository eventIssueRepository) {
		this.eventIssueRepository = eventIssueRepository;
	}

	public List<EventIssue> listAllEventIssues() {
		return this.eventIssueRepository.findAll();
	}
}
