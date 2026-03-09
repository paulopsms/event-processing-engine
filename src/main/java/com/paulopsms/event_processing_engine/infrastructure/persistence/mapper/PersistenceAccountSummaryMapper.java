package com.paulopsms.event_processing_engine.infrastructure.persistence.mapper;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.infrastructure.persistence.entity.AccountSummaryEntity;

public class PersistenceAccountSummaryMapper {

	public static AccountSummaryEntity toEntity(AccountSummary accountSummary) {

		AccountSummaryEntity entity = new AccountSummaryEntity();

		entity.setId(accountSummary.getId());
		entity.setAccountId(accountSummary.getAccountId());
		entity.setBalance(accountSummary.getBalance());
		entity.setTotalCredits(accountSummary.getTotalCredits());
		entity.setTotalDebits(accountSummary.getTotalDebits());
		entity.setValidEvents(accountSummary.getValidEvents());
		entity.setDuplicateEvents(accountSummary.getDuplicateEvents());
		entity.setConflictEvents(accountSummary.getConflictEvents());

		return entity;
	}

	public static AccountSummary toModel(AccountSummaryEntity entity) {

		AccountSummary model = new AccountSummary();

		model.setId(entity.getId());
		model.setAccountId(entity.getAccountId());
		model.setBalance(entity.getBalance());
		model.setTotalCredits(entity.getTotalCredits());
		model.setTotalDebits(entity.getTotalDebits());
		model.setValidEvents(entity.getValidEvents());
		model.setDuplicateEvents(entity.getDuplicateEvents());
		model.setConflictEvents(entity.getConflictEvents());

		return model;
	}

	public static void updateEntity(AccountSummaryEntity entity, AccountSummary accountSummary) {

		entity.setAccountId(accountSummary.getAccountId());
		entity.setBalance(accountSummary.getBalance());
		entity.setTotalCredits(accountSummary.getTotalCredits());
		entity.setTotalDebits(accountSummary.getTotalDebits());
		entity.setValidEvents(accountSummary.getValidEvents());
		entity.setDuplicateEvents(accountSummary.getDuplicateEvents());
		entity.setConflictEvents(accountSummary.getConflictEvents());
	}
}