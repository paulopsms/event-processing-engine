package com.paulopsms.event_processing_engine.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account_summary")
public class AccountSummary {

	@Id
	private String accountId;

	private BigDecimal balance;
	private BigDecimal totalCredits;
	private BigDecimal totalDebits;
	private long validEvents;
	private long duplicateEvents;
	private long conflictEvents;
	private Long version;

	protected AccountSummary() {
	}

	public AccountSummary(String accountId) {
		this.accountId = accountId;
		this.balance = BigDecimal.ZERO;
		this.totalCredits = BigDecimal.ZERO;
		this.totalDebits = BigDecimal.ZERO;
		this.validEvents = 0;
		this.duplicateEvents = 0;
		this.conflictEvents = 0;
	}

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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void apply(Event event) {

		Objects.requireNonNull(event, "Event is required.");

		if (event.isCredit()) {
			this.applyCredit(event);
		} else if (event.isDebit()) {
			this.applyDebit(event);
		}
	}

	private void applyCredit(Event event) {
		this.addBalance(event);

		this.addTotalCredits(event);

		this.incrementValidEvents();
	}

	private void applyDebit(Event event) {
		if (this.balanceLessThanDebitAmount(event)) {
			this.incrementConflictEvents();

			return;
		}

		this.subtractBalance(event);
		this.addTotalDebits(event);
		this.incrementValidEvents();
	}

	private boolean balanceLessThanDebitAmount(Event event) {
		return this.balance.compareTo(event.getAmount()) < 0;
	}

	private void addTotalDebits(Event event) {
		this.totalDebits = this.totalDebits.add(event.getAmount());
	}

	private void subtractBalance(Event event) {
		this.balance = this.balance.subtract(event.getAmount());
	}

	private void incrementConflictEvents() {
		this.conflictEvents++;
	}

	private void incrementValidEvents() {
		this.validEvents++;
	}

	private void addTotalCredits(Event event) {
		this.totalCredits = this.totalCredits.add(event.getAmount());
	}

	private void addBalance(Event event) {
		this.balance = this.balance.add(event.getAmount());
	}

	public void incrementDuplicate() {
		this.duplicateEvents++;
	}


}