package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetEventIssueService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final EventIssueRepository eventIssueRepository;

	public GetEventIssueService(EventIssueRepository eventIssueRepository) {
		this.eventIssueRepository = eventIssueRepository;
	}

	public List<EventIssue> findAllEventIssues() {
		List<EventIssue> eventIssues = this.eventIssueRepository.findAll();

		this.logger.info("Listing {} Events Issues .", eventIssues.size());

		return eventIssues;
	}
}
