package com.imcode.tools.jdbmtosql.repositories;

import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 16.01.17.
 */
public interface TransactionDomainEventsRepository extends JpaRepository<TransactionDomainEvents, Long> {
}
