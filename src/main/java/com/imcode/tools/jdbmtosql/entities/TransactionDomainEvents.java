package com.imcode.tools.jdbmtosql.entities;

import com.imcode.tools.jdbmtosql.entities.superclasses.LongIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * List of events for a single transaction. Events must always be consumed
 * in transaction units, in order to ensure that the result is consistent
 * with what happened in that transaction.
 */
@Entity
public class TransactionDomainEvents extends LongIdEntity implements Serializable{

    // Timestamp when the events were stored in the EventStore
    // Note that if events are sent from one store to another this timestamp
    // is updated when it is re-stored
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // List of events for this transaction
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ApplicationEvent.class)
    @JoinColumn(name = "transaction_domain_events_id")
    private List<ApplicationEvent> events;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<ApplicationEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ApplicationEvent> events) {
        this.events = events;
    }
}
