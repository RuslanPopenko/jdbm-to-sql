package com.imcode.tools.jdbmtosql.transfer.services.schedulerhelpers;

import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.repositories.TransactionDomainEventsRepository;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by ruslan on 17.01.17.
 */
@Service
public class EventsSchedulerHelper extends AbstractSchedulerHelper {

    private final TransactionDomainEventsRepository transactionDomainEventsRepository;

    @Autowired
    public EventsSchedulerHelper(TransactionDomainEventsRepository transactionDomainEventsRepository) {
        this.transactionDomainEventsRepository = transactionDomainEventsRepository;
    }

    @Override
    public Long save(Object target) throws Exception {
        LinkedList<TransactionDomainEvents> result = (LinkedList<TransactionDomainEvents>) target;
        transactionDomainEventsRepository.save(result);
        return result.peekLast().getTimestamp().getTime();
    }

}
