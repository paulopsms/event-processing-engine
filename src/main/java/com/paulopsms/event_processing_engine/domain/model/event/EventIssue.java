package com.paulopsms.event_processing_engine.domain.model.event;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventIssue {

	private UUID issueId;
	private String eventId;
	private IssueType type;
	private LocalDateTime detectedAt;

	public EventIssue() {
	}

	public EventIssue(String eventId, IssueType type) {
		this.issueId = UUID.randomUUID();
		this.eventId = eventId;
		this.type = type;
		this.detectedAt = LocalDateTime.now();
	}

	public UUID getIssueId() {
		return issueId;
	}

	public void setIssueId(UUID issueId) {
		this.issueId = issueId;
	}

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
