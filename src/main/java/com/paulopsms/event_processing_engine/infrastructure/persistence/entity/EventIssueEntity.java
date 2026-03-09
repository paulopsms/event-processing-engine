package com.paulopsms.event_processing_engine.infrastructure.persistence.entity;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_issue")
public class EventIssueEntity {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID issueId;

	private String eventId;

	@Enumerated(EnumType.STRING)
	private IssueType type;

	private LocalDateTime detectedAt;


	public UUID getIssueId() {
		return issueId;
	}

	public void setIssueId(UUID id) {
		this.issueId = id;
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
