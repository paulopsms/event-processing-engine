package com.paulopsms.event_processing_engine.infrastructure.persistence.entity;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.shared.exception.EventException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String eventId;
	private String accountId;
	private LocalDateTime ocurredAt;
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
