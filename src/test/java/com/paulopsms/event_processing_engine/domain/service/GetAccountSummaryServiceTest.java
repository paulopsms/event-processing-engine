package com.paulopsms.event_processing_engine.domain.service;

import com.paulopsms.event_processing_engine.domain.model.AccountSummary;
import com.paulopsms.event_processing_engine.domain.repository.AccountSummaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAccountSummaryServiceTest {

	@InjectMocks
	private GetAccountSummaryService getAccountSummaryService;

	@Mock
	private AccountSummaryRepository accountSummaryRepository;

	@Test
	public void shouldReturnEmptyListWhenNoAccountSummariesExist() {
		when(accountSummaryRepository.findAll()).thenReturn(Collections.emptyList());

		List<AccountSummary> allAccountSummaries = this.getAccountSummaryService.findAllAccountSummaries();

		assertNotNull(allAccountSummaries);
		Assertions.assertTrue(allAccountSummaries.isEmpty());
		verify(accountSummaryRepository, times(1)).findAll();
	}

	@Test
	public void shouldReturnListWithSummariesWhenAccountSummariesExist() {
		AccountSummary accountSummary = new AccountSummary("ACC-001");

		when(accountSummaryRepository.findAll()).thenReturn(Collections.singletonList(accountSummary));

		List<AccountSummary> allAccountSummaries = this.getAccountSummaryService.findAllAccountSummaries();

		assertNotNull(allAccountSummaries);
		assertFalse(allAccountSummaries.isEmpty());
		assertEquals(1, allAccountSummaries.size());
		verify(accountSummaryRepository, times(1)).findAll();
	}
}
