package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.PersistenceEventMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventAdapter implements EventRepository {

	private final EventJpaRepository eventJpaRepository;

	public EventAdapter(EventJpaRepository eventJpaRepository) {
		this.eventJpaRepository = eventJpaRepository;
	}

	@Override
	public Optional<Event> findByEventId(String eventId) {
		return this.eventJpaRepository.findByEventId(eventId).map(PersistenceEventMapper::toModel);
	}

	@Override
	public void save(Event event) {
		EventEntity eventEntity = PersistenceEventMapper.toModel(event);

		EventEntity saved = this.eventJpaRepository.save(eventEntity);

		PersistenceEventMapper.toModel(saved);
	}

	@Override
	public void deleteByEventId(String eventId) {
		this.eventJpaRepository.deleteByEventId(eventId);
	}

	@Override
	public List<Event> findByAccountIdOrderByOccurredAt(String accountId) {
		return this.eventJpaRepository.findByAccountIdOrderByOccurredAt(accountId)
				.stream()
				.map(PersistenceEventMapper::toModel)
				.collect(Collectors.toList());
	}
}
