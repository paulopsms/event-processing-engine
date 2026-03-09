package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.domain.enums.IssueType;
import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountSummaryServiceTest {

	@InjectMocks
	private AccountSummaryService accountSummaryService;

	@Mock
	private AccountSummaryRepository accountSummaryRepository;

	@Mock
	private EventRepository eventRepository;

	private Event event;
	private Event event2;
	private EventIssue eventIssueDuplicate;
	private EventIssue eventIssueConflict;
	private AccountSummary accountSummary;

	@BeforeEach
	public void setup() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-03-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		String eventId2 = "EVT-0002";
		LocalDateTime occurredAt2 = LocalDateTime.parse("2026-03-01T10:10:00");
		EventType debit = EventType.DEBIT;
		BigDecimal amount2 = new BigDecimal("300.00");

		event = new Event(eventId, accountId, occurredAt, credit, amount);
		event2 = new Event(eventId2, accountId, occurredAt2, debit, amount2);

		this.eventIssueDuplicate = new EventIssue(eventId, IssueType.DUPLICATE);
		this.eventIssueConflict = new EventIssue(eventId, IssueType.CONFLICT);

		this.accountSummary = new AccountSummary(accountId);
	}

	@Test
	public void shouldAggregateAccountSummary() {
		when(this.accountSummaryRepository.findByAccountId(event.getAccountId())).thenReturn(Optional.of(this.accountSummary));

		this.accountSummaryService.aggregate(event);

		assertEquals(new BigDecimal("1000.00"), this.accountSummary.getBalance());
		assertEquals(1, this.accountSummary.getValidEvents());
		assertEquals(new BigDecimal("1000.00"), this.accountSummary.getTotalCredits());
		assertEquals(BigDecimal.ZERO, this.accountSummary.getTotalDebits());
		verify(this.accountSummaryRepository).findByAccountId(anyString());
		verify(this.accountSummaryRepository).save(any(AccountSummary.class));
	}

	@Test
	public void shouldRecalculateAccountSummary() {
		List<Event> events = Arrays.asList(event, event2);

		when(this.eventRepository.findByAccountIdOrderByOccurredAt(event.getAccountId())).thenReturn(events);
		when(this.accountSummaryRepository.findByAccountId(event.getAccountId())).thenReturn(Optional.of(this.accountSummary));

		this.accountSummaryService.recalculateSummary(event);

		assertEquals(new BigDecimal("700.00"), this.accountSummary.getBalance());
		assertEquals(2, this.accountSummary.getValidEvents());
		assertEquals(new BigDecimal("1000.00"), this.accountSummary.getTotalCredits());
		assertEquals(new BigDecimal("300.00"), this.accountSummary.getTotalDebits());
		verify(this.eventRepository).findByAccountIdOrderByOccurredAt(anyString());
		verify(this.accountSummaryRepository).findByAccountId(anyString());
		verify(this.accountSummaryRepository).save(any(AccountSummary.class));
	}

	@Test
	public void shouldUpdateSummaryDuplicationCount() {
		when(this.accountSummaryRepository.findByAccountId(event.getAccountId())).thenReturn(Optional.of(this.accountSummary));

		this.accountSummaryService.updateCount(event, eventIssueDuplicate);

		assertEquals(1, this.accountSummary.getDuplicateEvents());
		assertEquals(0, this.accountSummary.getConflictEvents());
		verify(this.accountSummaryRepository).save(any(AccountSummary.class));
	}

	@Test
	public void shouldUpdateSummaryConflictCount() {
		when(this.accountSummaryRepository.findByAccountId(event.getAccountId())).thenReturn(Optional.of(this.accountSummary));

		this.accountSummaryService.updateCount(event, eventIssueConflict);

		assertEquals(0, this.accountSummary.getDuplicateEvents());
		assertEquals(1, this.accountSummary.getConflictEvents());
		verify(this.accountSummaryRepository).save(any(AccountSummary.class));
	}
}
