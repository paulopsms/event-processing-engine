package com.paulopsms.event_processing_engine.domain.factory;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.enums.IssueType;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.shared.exception.BusinessException;

import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.CONFLICT;
import static com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult.DUPLICATE;

public class EventIssueFactory {

	public static EventIssue createEventIssue(String eventId, DeduplicationResult result) {
		switch (result) {
			case DUPLICATE:
				return createEventIssueDuplicate(eventId);
			case CONFLICT:
				return createEventIssueConflict(eventId);
			default:
				throw new BusinessException("Invalid Event Type found.");
		}
	}

	private static EventIssue createEventIssueDuplicate(String eventId) {
		return new EventIssue(eventId, IssueType.DUPLICATE);
	}

	private static EventIssue createEventIssueConflict(String eventId) {
		return new EventIssue(eventId, IssueType.CONFLICT);
	}

}