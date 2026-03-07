package com.paulopsms.event_processing_engine.domain.repository;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;

import java.util.List;
import java.util.Optional;

public interface AccountSummaryRepository {

	Optional<AccountSummary> findByAccountId(String accountId);

	void save(AccountSummary summary);

	List<AccountSummary> findAll();
}
