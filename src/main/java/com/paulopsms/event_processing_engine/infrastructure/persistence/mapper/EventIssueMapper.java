package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.EventIssueEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface EventIssueMapper {

    EventIssue toModel(EventIssueEntity entity);

    EventIssueEntity toEntity(EventIssue dto);
}
