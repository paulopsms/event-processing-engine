package com.paulopsms.event_processing_engine.application.dto;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;

import java.time.LocalDateTime;

public class EventIssueResponse {

	private String eventId;
	private IssueType type;
	private LocalDateTime detectedAt;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public IssueType getType() {
		return type;
	}

	public void setType(IssueType type) {
		this.type = type;
	}

	public LocalDateTime getDetectedAt() {
		return detectedAt;
	}

	public void setDetectedAt(LocalDateTime detectedAt) {
		this.detectedAt = detectedAt;
	}
}
