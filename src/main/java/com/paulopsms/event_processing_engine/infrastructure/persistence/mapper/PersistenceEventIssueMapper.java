package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventIssueEntity;

public class PersistenceEventIssueMapper {

	public static EventIssueEntity toEntity(EventIssue issue) {

		EventIssueEntity entity = new EventIssueEntity();

		entity.setIssueId(issue.getIssueId());
		entity.setEventId(issue.getEventId());
		entity.setType(issue.getType());
		entity.setDetectedAt(issue.getDetectedAt());

		return entity;
	}

	public static EventIssue toModel(EventIssueEntity entity) {

		EventIssue model = new EventIssue();

		model.setIssueId(entity.getIssueId());
		model.setEventId(entity.getEventId());
		model.setType(entity.getType());
		model.setDetectedAt(entity.getDetectedAt());

		return model;
	}
}