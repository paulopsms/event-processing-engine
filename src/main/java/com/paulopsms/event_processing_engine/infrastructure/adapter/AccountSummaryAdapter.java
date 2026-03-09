package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.AccountSummaryEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.PersistenceAccountSummaryMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.AccountSummaryJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountSummaryAdapter implements AccountSummaryRepository {

	private final AccountSummaryJpaRepository accountSummaryJpaRepository;

	public AccountSummaryAdapter(AccountSummaryJpaRepository accountSummaryJpaRepository) {
		this.accountSummaryJpaRepository = accountSummaryJpaRepository;
	}

	@Override
	public Optional<AccountSummary> findByAccountId(String accountId) {
		return this.accountSummaryJpaRepository.findByAccountId(accountId).map(PersistenceAccountSummaryMapper::toModel);
	}

	@Override
	public void save(AccountSummary summary) {
		AccountSummaryEntity entity = accountSummaryJpaRepository.findByAccountId(summary.getAccountId()).orElse(null);

		if (entity == null)
			entity = PersistenceAccountSummaryMapper.toEntity(summary);
		else
			PersistenceAccountSummaryMapper.updateEntity(entity, summary);

		this.accountSummaryJpaRepository.save(entity);
	}

	@Override
	public List<AccountSummary> findAll() {
		return this.accountSummaryJpaRepository.findAll().stream()
				.map(PersistenceAccountSummaryMapper::toModel)
				.collect(Collectors.toList());
	}
}
