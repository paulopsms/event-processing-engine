package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.service.GetEventIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetEventIssuesUseCase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public final GetEventIssueService getEventIssueService;

	public GetEventIssuesUseCase(GetEventIssueService getEventIssueService) {
		this.getEventIssueService = getEventIssueService;
	}

	public List<EventIssue> listAllSummaries() {

		this.logger.info("Listing Events Issues.");

		return this.getEventIssueService.findAllEventIssues();
	}
}
