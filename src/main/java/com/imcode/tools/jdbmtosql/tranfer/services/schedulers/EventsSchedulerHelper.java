package com.imcode.tools.jdbmtosql.tranfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.repositories.DatabasesInfoRepository;
import com.imcode.tools.jdbmtosql.repositories.TransactionDomainEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ruslan on 17.01.17.
 */
@Service
public class EventsSchedulerHelper {

    private final TransactionDomainEventsRepository transactionDomainEventsRepository;
    private final DatabasesInfoRepository databasesInfoRepository;

    @Autowired
    public EventsSchedulerHelper(TransactionDomainEventsRepository transactionDomainEventsRepository,
                                 DatabasesInfoRepository databasesInfoRepository) {
        this.transactionDomainEventsRepository = transactionDomainEventsRepository;
        this.databasesInfoRepository = databasesInfoRepository;
    }

    @Transactional
    public TransactionDomainEvents save(TransactionDomainEvents transactionDomainEvents) {
        return transactionDomainEventsRepository.save(transactionDomainEvents);
    }

    @Transactional
    public DatabasesInfo save(DatabasesInfo databasesInfo) {
        return databasesInfoRepository.save(databasesInfo);
    }

    public DatabasesInfo findBy(HdbmDatabasesDescription description) {
        return databasesInfoRepository.findByDatabasesDescription(description);
    }


}
