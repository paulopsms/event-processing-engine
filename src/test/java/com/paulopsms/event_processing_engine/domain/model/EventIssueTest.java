package com.paulopsms.event_processing_engine.domain.model;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventIssueTest {

	@Test
	void shouldCreateEventIssueUsingConstructor() {
		String eventId = "EVT-0001";
		IssueType type = IssueType.DUPLICATE;

		EventIssue issue = new EventIssue(eventId, type);

		assertNotNull(issue.getIssueId());
		assertEquals(eventId, issue.getEventId());
		assertEquals(type, issue.getType());
		assertNotNull(issue.getDetectedAt());
	}
}
