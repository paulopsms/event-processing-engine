package com.paulopsms.event_processing_engine.domain.model;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.shared.exception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event {

	private UUID id;
	private String eventId;
	private String accountId;
	private LocalDateTime ocurredAt;
	private EventType type;
	private BigDecimal amount;

	public Event() {
		this.id = UUID.randomUUID();
	}

	public Event(String eventId, String accountId, LocalDateTime ocurredAt, EventType type, BigDecimal amount) {
		this.id = UUID.randomUUID();
		this.eventId = eventId;
		this.accountId = accountId;
		this.ocurredAt = ocurredAt;
		this.type = type;
		this.amount = amount;

		if (this.isAmountNegative())
			throw new BusinessException("Amount must be positive.");
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getOccurredAt() {
		return ocurredAt;
	}

	public void setOcurredAt(LocalDateTime ocurredAt) {
		this.ocurredAt = ocurredAt;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isAmountNegative() {
		return this.amount.compareTo(BigDecimal.ZERO) <= 0;
	}

	public boolean isCredit() {
		return EventType.CREDIT.equals(this.type);
	}

	public boolean isDebit() {
		return EventType.DEBIT.equals(this.type);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return Objects.equals(eventId, event.eventId) && Objects.equals(accountId, event.accountId) && Objects.equals(ocurredAt, event.ocurredAt) && type == event.type && Objects.equals(amount, event.amount);
	}
}
