package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.AccountSummaryEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AccountSummaryMapper {

	AccountSummary toModel(AccountSummaryEntity entity);

	AccountSummaryEntity toEntity(AccountSummary dto);
}
