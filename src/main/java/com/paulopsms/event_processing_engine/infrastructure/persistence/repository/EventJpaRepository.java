package com.paulopsms.event_processing_engine.infrastructure.persistence.repository;

import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventEntity, String> {

}
