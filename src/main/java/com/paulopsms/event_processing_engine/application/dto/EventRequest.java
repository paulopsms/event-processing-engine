package com.paulopsms.event_processing_engine.application.dto;

import com.paulopsms.event_processing_engine.domain.enums.EventType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventRequest {
	private String eventId;
	private String accountId;
	private LocalDateTime occurredAt;
	private EventType type;
	private BigDecimal amount;

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
		return occurredAt;
	}

	public void setoccurredAt(LocalDateTime occurredAt) {
		this.occurredAt = occurredAt;
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
}
