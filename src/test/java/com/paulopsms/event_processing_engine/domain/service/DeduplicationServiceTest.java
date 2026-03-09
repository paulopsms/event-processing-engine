package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.domain.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeduplicationServiceTest {

	private DeduplicationService deduplicationService;

	@BeforeEach
	public void setup() {
		this.deduplicationService = new DeduplicationService();
	}

	@Test
	public void shouldReturnDuplicateWhenEventsAreEqual() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event existingEvent = new Event(eventId, accountId, occurredAt, credit, amount);

		Event newEvent = new Event(eventId, accountId, occurredAt, credit, amount);

		DeduplicationResult result = this.deduplicationService.validateExistingEvent(existingEvent, newEvent);

		assertEquals(DeduplicationResult.DUPLICATE, result);
	}

	@Test
	public void shouldReturnConflictWhenEventsAreDifferent() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event existingEvent = new Event(eventId, accountId, occurredAt, credit, amount);

		EventType debit = EventType.DEBIT;

		Event newEvent = new Event(eventId, accountId, occurredAt, debit, amount);

		DeduplicationResult result = this.deduplicationService.validateExistingEvent(existingEvent, newEvent);

		assertEquals(DeduplicationResult.CONFLICT, result);
	}
}
