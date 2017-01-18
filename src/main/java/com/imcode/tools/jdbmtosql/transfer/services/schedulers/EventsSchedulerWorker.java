package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerWorker;
import com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers.EventsSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class EventsSchedulerWorker extends AbstractSchedulerWorker {

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
    public SchedulerHelper getSchedulerHelper() {
        return eventsSchedulerHelper;
    }

    @Override
    public HdbmDatabasesDescription getDatabaseDescription() {
        return HdbmDatabasesDescription.EVENTS;
    }

    @Override
    public BTree getDatabase() {
        return eventsDb;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo, Long timestamp) throws Exception {
        List<TransactionDomainEvents> result = new LinkedList<>();

        for (String transactionalDomainEventsJson : entitiesJson) {
            Object mappedEntity = entityMapper.map(transactionalDomainEventsJson);
            Assert.isTrue(mappedEntity.getClass().equals(Constants.EVENTS_MAP_CLASS), "Mapped entity isn't instance of " + Constants.EVENTS_MAP_CLASS);
            TransactionDomainEvents entity = (TransactionDomainEvents) mappedEntity;
            result.add(entity);
            timestamp = entity.getTimestamp().getTime();
        }

        dbInfo.setLastProcessedTimestamp(timestamp);

        eventsSchedulerHelper.save(result, dbInfo);
    }
}
