package com.paulopsms.event_processing_engine.application.dto;

import java.math.BigDecimal;

public class AccountSummaryResponse {

	private String accountId;
	private BigDecimal balance;
	private BigDecimal totalCredits;
	private BigDecimal totalDebits;
	private long validEvents;
	private long duplicateEvents;
	private long conflictEvents;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(BigDecimal totalCredits) {
		this.totalCredits = totalCredits;
	}

	public BigDecimal getTotalDebits() {
		return totalDebits;
	}

	public void setTotalDebits(BigDecimal totalDebits) {
		this.totalDebits = totalDebits;
	}

	public long getValidEvents() {
		return validEvents;
	}

	public void setValidEvents(long validEvents) {
		this.validEvents = validEvents;
	}

	public long getDuplicateEvents() {
		return duplicateEvents;
	}

	public void setDuplicateEvents(long duplicateEvents) {
		this.duplicateEvents = duplicateEvents;
	}

	public long getConflictEvents() {
		return conflictEvents;
	}

	public void setConflictEvents(long conflictEvents) {
		this.conflictEvents = conflictEvents;
	}
}
