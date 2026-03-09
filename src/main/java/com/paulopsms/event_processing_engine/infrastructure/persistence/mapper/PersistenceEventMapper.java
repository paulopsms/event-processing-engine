package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventEntity;

public class PersistenceEventMapper {

	public static EventEntity toModel(Event event) {

		EventEntity entity = new EventEntity();

		entity.setId(event.getId());
		entity.setEventId(event.getEventId());
		entity.setOccurredAt(event.getOccurredAt());
		entity.setType(event.getType());
		entity.setAccountId(event.getAccountId());
		entity.setAmount(event.getAmount());
		entity.setId(event.getId());

		return entity;
	}

	public static Event toModel(EventEntity entity) {

		Event event = new Event();

		event.setId(entity.getId());
		event.setEventId(entity.getEventId());
		event.setOccurredAt(entity.getOccurredAt());
		event.setType(entity.getType());
		event.setAccountId(entity.getAccountId());
		event.setAmount(entity.getAmount());
		event.setId(entity.getId());

		return event;
	}
}