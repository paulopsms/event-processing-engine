package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface EventMapper {

	Event toModel(EventEntity entity);

	EventEntity toEntity(Event dto);
}
