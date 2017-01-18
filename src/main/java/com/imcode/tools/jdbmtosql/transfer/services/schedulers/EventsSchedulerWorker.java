package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerWorker;
import com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers.EventsSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import com.imcode.tools.jdbmtosql.utils.Utils;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class EventsSchedulerWorker implements SchedulerWorker {

    private final EventsSchedulerHelper eventsSchedulerHelper;
    private final BTree eventsDb;
    private final EntityMapper entityMapper;

    @Autowired
    public EventsSchedulerWorker(EventsSchedulerHelper eventsSchedulerHelper,
                                 @Qualifier("eventsDb") BTree eventsDb,
                                 @Qualifier("eventsEntityMapper") EntityMapper entityMapper) {
        this.eventsSchedulerHelper = eventsSchedulerHelper;
        this.eventsDb = eventsDb;
        this.entityMapper = entityMapper;
    }

    @Override
    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public void scheduleWork() throws Exception {

        DatabasesInfo eventsInfo = eventsSchedulerHelper.findBy(HdbmDatabasesDescription.EVENTS);

        Long timestamp;
        if (eventsInfo == null) {
            eventsInfo = new DatabasesInfo();
            eventsInfo.setHdbmDatabasesDescription(HdbmDatabasesDescription.EVENTS);
            timestamp = 0L;
        } else {
            timestamp = eventsInfo.getLastProcessedTimestamp() + 1;
        }

        List<String> transactionalDomainEventsJsons =
                Utils.getJsonDatabaseRecords(eventsDb, timestamp);

        List<TransactionDomainEvents> result = new LinkedList<>();

        for (String transactionalDomainEventsJson : transactionalDomainEventsJsons) {
            Object mappedEntity = entityMapper.map(transactionalDomainEventsJson);
            Assert.isTrue(mappedEntity.getClass().equals(Constants.EVENTS_MAP_CLASS), "Mapped entity isn't instance of " + Constants.EVENTS_MAP_CLASS);
            TransactionDomainEvents entity = (TransactionDomainEvents) mappedEntity;
            result.add(entity);
            timestamp = entity.getTimestamp().getTime();
        }

        eventsInfo.setLastProcessedTimestamp(timestamp);

        eventsSchedulerHelper.save(result, eventsInfo);
    }

}
