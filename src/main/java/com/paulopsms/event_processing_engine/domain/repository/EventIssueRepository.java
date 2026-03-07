package com.paulopsms.event_processing_engine.domain.repository;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;

import java.util.Optional;

public interface EventIssueRepository {

	Optional<EventIssue> findByEventId(String eventId);

	void save(EventIssue eventIssue);
}
