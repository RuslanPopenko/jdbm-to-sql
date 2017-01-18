package com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.repositories.TransactionDomainEventsRepository;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public void save(List<TransactionDomainEvents> transactionDomainEventsList, DatabasesInfo databasesInfo) {
        transactionDomainEventsRepository.save(transactionDomainEventsList);
        databasesInfoRepository.save(databasesInfo);
    }

}
