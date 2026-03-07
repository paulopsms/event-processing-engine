package com.paulopsms.event_processing_engine.application.mapper;

import com.paulopsms.event_processing_engine.application.dto.EventIssueResponse;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;

public class RestEventIssueMapper {

	public static EventIssueResponse toResponse(EventIssue issue) {

		EventIssueResponse response = new EventIssueResponse();

		response.setEventId(issue.getEventId());
		response.setType(issue.getType());
		response.setDetectedAt(issue.getDetectedAt());

		return response;
	}
}