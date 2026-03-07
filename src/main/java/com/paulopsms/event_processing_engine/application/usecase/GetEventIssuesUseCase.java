package com.paulopsms.event_processing_engine.application.usecase;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.service.GetEventIssueService;

import java.util.List;

public class GetEventIssuesUseCase {

	public final GetEventIssueService getEventIssueService;

	public GetEventIssuesUseCase(GetEventIssueService getEventIssueService) {
		this.getEventIssueService = getEventIssueService;
	}

	public List<EventIssue> listAllSummaries() {
		return this.getEventIssueService.listAllEventIssues();
	}
}
