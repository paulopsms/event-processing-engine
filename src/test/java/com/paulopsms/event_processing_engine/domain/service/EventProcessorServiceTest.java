package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.DeduplicationResult;
import com.paulopsms.event_processing_engine.domain.enums.EventType;
import com.paulopsms.event_processing_engine.domain.model.Event;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import com.paulopsms.event_processing_engine.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventProcessorServiceTest {

	@InjectMocks
	private EventProcessorService eventProcessorService;

	@Mock
	private DeduplicationService deduplicationService;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private EventIssueRepository eventIssueRepository;

	@Mock
	private AccountSummaryService accountSummaryService;

	private Event event;
	private Event duplicatedEvent;
	private Event conflictEvent;

	@BeforeEach
	public void setup() {
		String eventId = "EVT-0001";
		String accountId = "ACC-001";
		LocalDateTime occurredAt = LocalDateTime.parse("2026-02-01T10:00:00");
		EventType credit = EventType.CREDIT;
		BigDecimal amount = new BigDecimal("1000.00");

		EventType debit = EventType.DEBIT;
		BigDecimal conflictAmount = new BigDecimal("300.00");

		event = new Event(eventId, accountId, occurredAt, credit, amount);
		duplicatedEvent = new Event(eventId, accountId, occurredAt, credit, amount);
		conflictEvent = new Event(eventId, accountId, occurredAt, debit, conflictAmount);
	}

	@Test
	public void shouldProcessEventSuccessfully() {
		when(this.eventRepository.findByEventId(event.getEventId())).thenReturn(Optional.empty());

		this.eventProcessorService.process(event);

		verify(this.eventRepository).findByEventId(Mockito.anyString());
		verify(this.eventRepository).save(any(Event.class));
		verify(this.accountSummaryService).aggregate(any(Event.class));
		verify(this.accountSummaryService, never()).updateCount(any(Event.class), any(EventIssue.class));
		verify(this.accountSummaryService, never()).recalculateSummary(any(Event.class));
		verify(this.eventIssueRepository, never()).save(any(EventIssue.class));
		verify(this.eventRepository, never()).deleteByEventId(Mockito.anyString());
	}

	@Test
	public void shouldDetectDuplicatedEvent() {
		when(this.eventRepository.findByEventId(duplicatedEvent.getEventId())).thenReturn(Optional.of(event));
		when(this.deduplicationService.validateExistingEvent(event, duplicatedEvent)).thenReturn(DeduplicationResult.DUPLICATE);

		this.eventProcessorService.process(duplicatedEvent);

		verify(this.eventRepository).findByEventId(Mockito.anyString());
		verify(this.eventIssueRepository).save(any(EventIssue.class));
		verify(this.accountSummaryService).updateCount(any(Event.class), any(EventIssue.class));
		verify(this.eventRepository, never()).save(any(Event.class));
		verify(this.accountSummaryService, never()).aggregate(any(Event.class));
		verify(this.accountSummaryService, never()).recalculateSummary(any(Event.class));
		verify(this.eventRepository, never()).deleteByEventId(Mockito.anyString());
	}

	@Test
	public void shouldDetectConflitedEvent() {
		when(this.eventRepository.findByEventId(conflictEvent.getEventId())).thenReturn(Optional.of(event));
		when(this.deduplicationService.validateExistingEvent(event, duplicatedEvent)).thenReturn(DeduplicationResult.CONFLICT);

		this.eventProcessorService.process(duplicatedEvent);

		verify(this.eventRepository).findByEventId(Mockito.anyString());
		verify(this.eventIssueRepository).save(any(EventIssue.class));
		verify(this.accountSummaryService).updateCount(any(Event.class), any(EventIssue.class));
		verify(this.eventRepository, never()).save(any(Event.class));
		verify(this.accountSummaryService, never()).aggregate(any(Event.class));
		verify(this.accountSummaryService).recalculateSummary(any(Event.class));
		verify(this.eventRepository).deleteByEventId(Mockito.anyString());
	}
}
