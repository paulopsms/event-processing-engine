package com.paulopsms.event_processing_engine.infrastructure.persistence.repository;

import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventIssueJpaRepository extends JpaRepository<EventIssueEntity, String> {

	Optional<EventIssueEntity> findByEventId(String eventId);
}
