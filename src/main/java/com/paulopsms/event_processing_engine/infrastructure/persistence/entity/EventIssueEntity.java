package com.paulopsms.event_processing_engine.infrastructure.persistence.entity;

import com.paulopsms.event_processing_engine.domain.enums.IssueType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_issue")
public class EventIssueEntity {

	@Id
	@GeneratedValue
	private UUID issueId;

	@ManyToOne
	@JoinColumn(name = "uuid")
	private EventEntity event;
	private IssueType type;
	private LocalDateTime detectedAt;

	public UUID getIssueId() {
		return issueId;
	}

	public void setIssueId(UUID id) {
		this.issueId = id;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
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
