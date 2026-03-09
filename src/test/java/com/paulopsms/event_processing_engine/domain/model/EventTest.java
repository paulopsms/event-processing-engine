package com.paulopsms.event_processing_engine.domain.model;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.shared.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

	@Test
	public void shouldCreateEventUsingEmptyContructorSuccessfully() {
		Event event = new Event();

		assertNotNull(event.getId());
	}

	@Test
	public void shouldCreateEventUsingAllArgsContructorSuccessfully() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event event = new Event(eventId, accountId, occurredAt, credit, amount);

		assertNotNull(event.getId());
		assertEquals(eventId, event.getEventId());
		assertEquals(accountId, event.getAccountId());
		assertEquals(occurredAt, event.getOccurredAt());
		assertEquals(credit, event.getType());
		assertEquals(amount, event.getAmount());
	}

	@Test
	public void whenCreatingNewEventWithNegativeAmount_shouldThrowAnException() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("-100.00");

		BusinessRuntimeException exception = assertThrows(BusinessRuntimeException.class, () -> new Event(eventId, accountId, occurredAt, credit, amount));

		assertEquals("Amount must be positive.", exception.getMessage());
	}
}
