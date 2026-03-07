package com.paulopsms.event_processing_engine.infrastructure.adapter;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.AccountSummaryEntity;
import com.paulopsms.event_processing_engine.infrastructure.persistence.mapper.AccountSummaryMapper;
import com.paulopsms.event_processing_engine.infrastructure.persistence.repository.AccountSummaryJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountSummaryAdapter implements AccountSummaryRepository {

	private final AccountSummaryJpaRepository accountSummaryJpaRepository;
	private final AccountSummaryMapper accountSummaryMapper;

	public AccountSummaryAdapter(AccountSummaryJpaRepository accountSummaryJpaRepository, AccountSummaryMapper accountSummaryMapper) {
		this.accountSummaryJpaRepository = accountSummaryJpaRepository;
		this.accountSummaryMapper = accountSummaryMapper;
	}

	@Override
	public Optional<AccountSummary> findByAccountId(String accountId) {
		return this.accountSummaryJpaRepository.findByAccountId(accountId).map(this.accountSummaryMapper::toModel);
	}

	@Override
	public void save(AccountSummary summary) {
		AccountSummaryEntity accountSummaryEntity = this.accountSummaryMapper.toEntity(summary);

		this.accountSummaryJpaRepository.save(accountSummaryEntity);
	}

	@Override
	public List<AccountSummary> findAll() {
		return this.accountSummaryJpaRepository.findAll().stream()
				.map(this.accountSummaryMapper::toModel)
				.collect(Collectors.toList());
	}
}
