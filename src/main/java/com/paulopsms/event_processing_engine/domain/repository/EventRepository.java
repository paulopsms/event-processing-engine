package com.paulopsms.event_processing_engine.domain.repository;

import com.paulopsms.event_processing_engine.domain.model.Event;

import java.util.Optional;

public interface EventRepository {

	Optional<Event> findByEventId(String eventId);

	void save(Event event);

	Optional<Event> findByEventId(String eventId);
}
