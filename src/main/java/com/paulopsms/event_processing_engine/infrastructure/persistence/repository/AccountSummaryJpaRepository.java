package com.paulopsms.event_processing_engine.infrastructure.persistence.repository;

import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.AccountSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountSummaryJpaRepository extends JpaRepository<AccountSummaryEntity, String> {

	Optional<AccountSummaryEntity> findByAccountId(String accountId);

}
