package com.paulopsms.event_processing_engine.application.mapper;

import com.paulopsms.event_processing_engine.application.dto.EventRequest;
import com.paulopsms.event_processing_engine.domain.model.Event;

public class RestEventMapper {

	public static Event toModel(EventRequest request) {

		Event model = new Event();

		model.setEventId(request.getEventId());
		model.setAccountId(request.getAccountId());
		model.setOccurredAt(request.getOccurredAt());
		model.setType(request.getType());
		model.setAmount(request.getAmount());

		return model;
	}
}