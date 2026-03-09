package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;
import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.model.EventIssue;
import com.paulopsms.event_processing_engine.domain.repository.EventIssueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetEventIssueServiceTest {

	@InjectMocks
	private GetEventIssueService getEventIssueService;

	@Mock
	private EventIssueRepository eventIssueRepository;

	@Test
	public void shouldReturnEmptyListWhenNoAccountSummariesExist() {
		when(eventIssueRepository.findAll()).thenReturn(Collections.emptyList());

		List<EventIssue> allEventIssues = this.getEventIssueService.findAllEventIssues();

		assertNotNull(allEventIssues);
		Assertions.assertTrue(allEventIssues.isEmpty());
		verify(eventIssueRepository, times(1)).findAll();
	}

	@Test
	public void shouldReturnListWithSummariesWhenAccountSummariesExist() {
		EventIssue eventIssue = new EventIssue("EVT-0001", IssueType.DUPLICATE);

		when(eventIssueRepository.findAll()).thenReturn(Collections.singletonList(eventIssue));

		List<EventIssue> allEventIssues = this.getEventIssueService.findAllEventIssues();

		assertNotNull(allEventIssues);
		assertFalse(allEventIssues.isEmpty());
		assertEquals(1, allEventIssues.size());
		verify(eventIssueRepository, times(1)).findAll();
	}
}
