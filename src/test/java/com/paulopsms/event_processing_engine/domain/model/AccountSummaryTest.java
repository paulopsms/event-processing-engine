package com.paulopsms.event_processing_engine.domain.model;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.domain.enums.IssueType;
import com.paulopsms.event_processing_engine.shared.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AccountSummaryTest {

	@Test
	public void shouldCreateAccountSummaryUsingEmptyContructorSuccessfully() {
		AccountSummary accountSummary = new AccountSummary();

		assertNotNull(accountSummary);
	}

	@Test
	public void shouldCreateAccountSummaryWithAccountIdContructorSuccessfully() {
		String accountId = "ACC-001";

		AccountSummary accountSummary = new AccountSummary(accountId);

		assertNotNull(accountSummary);
		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(BigDecimal.ZERO, accountSummary.getBalance());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalCredits());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(0, accountSummary.getDuplicateEvents());
		assertEquals(0, accountSummary.getValidEvents());
	}

	@Test
	public void whenApplyingNullEvent_shouldThrowAnException() {
		String accountId = "ACC-001";
		Event event = null;

		AccountSummary accountSummary = new AccountSummary(accountId);

		assertNotNull(accountSummary);
		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(BigDecimal.ZERO, accountSummary.getBalance());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalCredits());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(0, accountSummary.getDuplicateEvents());
		assertEquals(0, accountSummary.getValidEvents());

		BusinessRuntimeException exception = assertThrows(BusinessRuntimeException.class, () -> accountSummary.apply(event));

		assertEquals("Event is required.", exception.getMessage());


	}

	@Test
	public void whenApplyingCreditEventToSummary_thenShouldIncreaseTheAccountBalance() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime ocurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event event = new Event(eventId, accountId, ocurredAt, credit, amount);

		AccountSummary accountSummary = new AccountSummary(event.getAccountId());

		accountSummary.apply(event);

		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(amount, accountSummary.getBalance());
		assertEquals(amount, accountSummary.getTotalCredits());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(1, accountSummary.getValidEvents());
	}

	@Test
	public void whenApplyingDebitEventToSummary_thenShouldReduceTheAccountBalance() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime ocurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.DEBIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event event = new Event(eventId, accountId, ocurredAt, credit, amount);

		AccountSummary accountSummary = new AccountSummary(event.getAccountId());

		accountSummary.apply(event);

		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(amount.negate(), accountSummary.getBalance());
		assertEquals(amount, accountSummary.getTotalDebits());
		assertEquals(1, accountSummary.getValidEvents());
	}

	@Test
	public void whenApplyingCreditAndDebitEventsToSummary_thenShouldIncreaseAndReduceTheAccountBalance() {
		String creditEventId = "EVT-0001";
		String creditAccountId = "ACC-001";
		LocalDateTime creditOcurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal creditAmount = new BigDecimal("1000.00");

		Event creditEvent = new Event(creditEventId, creditAccountId, creditOcurredAt, credit, creditAmount);

		String debitEventId = "EVT-0002";
		String debitAccountId = "ACC-001";
		LocalDateTime debitOcurredAt = LocalDateTime.parse("2026-02-01T10:05:00");
		EventType debit = EventType.DEBIT;
		BigDecimal debitAmount = new BigDecimal("100.00");

		Event debitEvent = new Event(debitEventId, debitAccountId, debitOcurredAt, debit, debitAmount);

		assertNotNull(creditEvent.getEventId());
		assertNotNull(debitEvent.getEventId());

		AccountSummary accountSummary = new AccountSummary(creditEvent.getAccountId());

		accountSummary.apply(creditEvent);

		assertEquals(creditAccountId, accountSummary.getAccountId());
		assertEquals(creditAmount, accountSummary.getBalance());
		assertEquals(creditAmount, accountSummary.getTotalCredits());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(1, accountSummary.getValidEvents());

		accountSummary.apply(debitEvent);

		assertEquals(debitAccountId, accountSummary.getAccountId());
		assertEquals(creditAmount.subtract(debitAmount), accountSummary.getBalance());
		assertEquals(creditAmount, accountSummary.getTotalCredits());
		assertEquals(debitAmount, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(2, accountSummary.getValidEvents());
	}

	@Test
	public void whenIncrementingConflitToSummary_thenShouldIncreaseSummaryConflictCount() {
		IssueType conflict = IssueType.CONFLICT;

		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime ocurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.DEBIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event event = new Event(eventId, accountId, ocurredAt, credit, amount);

		AccountSummary accountSummary = new AccountSummary(event.getAccountId());

		accountSummary.increment(conflict);

		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(BigDecimal.ZERO, accountSummary.getBalance());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(1, accountSummary.getConflictEvents());
		assertEquals(0, accountSummary.getValidEvents());
		assertEquals(0, accountSummary.getDuplicateEvents());
	}

	@Test
	public void whenIncrementingDuplicateToSummary_thenShouldIncreaseSummaryDuplicateCount() {
		IssueType conflict = IssueType.DUPLICATE;

		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime ocurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.DEBIT;
		BigDecimal amount = new BigDecimal("1000.00");

		Event event = new Event(eventId, accountId, ocurredAt, credit, amount);

		AccountSummary accountSummary = new AccountSummary(event.getAccountId());

		accountSummary.increment(conflict);

		assertEquals(accountId, accountSummary.getAccountId());
		assertEquals(BigDecimal.ZERO, accountSummary.getBalance());
		assertEquals(BigDecimal.ZERO, accountSummary.getTotalDebits());
		assertEquals(0, accountSummary.getConflictEvents());
		assertEquals(0, accountSummary.getValidEvents());
		assertEquals(1, accountSummary.getDuplicateEvents());
	}
}
