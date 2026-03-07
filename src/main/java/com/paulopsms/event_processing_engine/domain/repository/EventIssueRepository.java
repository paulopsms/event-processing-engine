package com.paulopsms.event_processing_engine.domain.repository;

import com.paulopsms.event_processing_engine.domain.model.event.Event;

import java.util.Optional;

public interface EventIssueRepository {

	void save(Event event);
}
