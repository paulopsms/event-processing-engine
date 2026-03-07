package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.EventMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventJpaRepository;

import java.util.List;
import java.util.Optional;

public class EventAdapter implements EventRepository {

	private final EventJpaRepository eventJpaRepository;
	private final EventMapper eventMapper;

	public EventAdapter(EventJpaRepository eventJpaRepository, EventMapper eventMapper) {
		this.eventJpaRepository = eventJpaRepository;
		this.eventMapper = eventMapper;
	}

	@Override
	public Optional<Event> findByEventId(String eventId) {
		return this.eventJpaRepository.findByEventId(eventId).map(this.eventMapper::toModel);
	}

	@Override
	public void save(Event event) {
		EventEntity eventEntity = this.eventMapper.toEntity(event);

		this.eventJpaRepository.save(eventEntity);
	}

	@Override
	public void deleteByEventId(String eventId) {
		this.eventJpaRepository.deleteById(eventId);
	}

	@Override
	public List<Event> findByAccountIdOrderByOcurredAt(String accountId) {
		return this.eventJpaRepository.findByAccountIdOrderByOcurredAt(accountId);
	}
}
