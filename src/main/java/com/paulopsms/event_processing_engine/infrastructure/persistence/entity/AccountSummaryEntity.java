package com.paulopsms.event_processing_engine.infrastructure.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
@Table(name = "account_summary")
public class AccountSummaryEntity {

    @Id
    private String accountId;

    private BigDecimal balance;
    private BigDecimal totalCredits;
    private BigDecimal totalDebits;
    private long validEvents;
    private long duplicateEvents;
    private long conflictEvents;

    @Version
    private Long version;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(BigDecimal totalCredits) {
        this.totalCredits = totalCredits;
    }

    public BigDecimal getTotalDebits() {
        return totalDebits;
    }

    public void setTotalDebits(BigDecimal totalDebits) {
        this.totalDebits = totalDebits;
    }

    public long getValidEvents() {
        return validEvents;
    }

    public void setValidEvents(long validEvents) {
        this.validEvents = validEvents;
    }

    public long getDuplicateEvents() {
        return duplicateEvents;
    }

    public void setDuplicateEvents(long duplicateEvents) {
        this.duplicateEvents = duplicateEvents;
    }

    public long getConflictEvents() {
        return conflictEvents;
    }

    public void setConflictEvents(long conflictEvents) {
        this.conflictEvents = conflictEvents;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}