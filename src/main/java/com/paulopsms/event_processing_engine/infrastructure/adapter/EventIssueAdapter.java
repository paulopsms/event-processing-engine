package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventIssueEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.EventIssueMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.EventIssueJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventIssueAdapter implements EventIssueRepository {

	private final EventIssueJpaRepository eventIssueJpaRepository;
	private final EventIssueMapper eventIssueMapper;

	public EventIssueAdapter(EventIssueJpaRepository eventIssueJpaRepository, EventIssueMapper eventIssueMapper) {
		this.eventIssueJpaRepository = eventIssueJpaRepository;
		this.eventIssueMapper = eventIssueMapper;
	}

	@Override
	public Optional<EventIssue> findByEventId(String eventId) {
		return this.eventIssueJpaRepository.findByEventId(eventId).map(this.eventIssueMapper::toModel);
	}

	@Override
	public void save(EventIssue eventIssue) {
		EventIssueEntity eventIssueEntity = this.eventIssueMapper.toEntity(eventIssue);

		this.eventIssueJpaRepository.save(eventIssueEntity);
	}

	@Override
	public List<EventIssue> findAll() {
		return this.eventIssueJpaRepository.findAll().stream().map(this.eventIssueMapper::toModel).collect(Collectors.toList());
	}
}
