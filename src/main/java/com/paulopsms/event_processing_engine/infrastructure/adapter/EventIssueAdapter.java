package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventIssueEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.PersistenceEventIssueMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventIssueJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventIssueAdapter implements EventIssueRepository {

	private final EventIssueJpaRepository eventIssueJpaRepository;

	public EventIssueAdapter(EventIssueJpaRepository eventIssueJpaRepository) {
		this.eventIssueJpaRepository = eventIssueJpaRepository;
	}

	@Override
	public Optional<EventIssue> findByEventId(String eventId) {
		return this.eventIssueJpaRepository.findByEventId(eventId).map(PersistenceEventIssueMapper::toModel);
	}

	@Override
	public void save(EventIssue eventIssue) {
		EventIssueEntity eventIssueEntity = PersistenceEventIssueMapper.toEntity(eventIssue);

		this.eventIssueJpaRepository.save(eventIssueEntity);
	}

	@Override
	public List<EventIssue> findAll() {
		return this.eventIssueJpaRepository.findAll().stream().map(PersistenceEventIssueMapper::toModel).collect(Collectors.toList());
	}
}
