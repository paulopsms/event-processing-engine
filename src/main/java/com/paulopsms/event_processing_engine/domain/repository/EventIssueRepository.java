package com.paulopsms.event_processing_engine.domain.repository;

import com.paulopsms.event_processing_engine.domain.model.event.EventIssue;

public interface EventIssueRepository {

	void save(EventIssue eventIssue);
}
