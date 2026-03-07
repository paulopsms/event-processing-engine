package com.paulopsms.event_processing_engine.domain.model.event;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.shared.exception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

	private UUID id;
	private String eventId;
	private String accountId;
	private LocalDateTime ocurredAt;
	private EventType type;
	private BigDecimal amount;

	public Event() {
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

	public LocalDateTime getOcurredAt() {
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
}
