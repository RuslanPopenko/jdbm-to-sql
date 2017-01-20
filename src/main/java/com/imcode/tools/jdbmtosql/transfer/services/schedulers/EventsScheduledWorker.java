package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractScheduledWorker;
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
public class EventsScheduledWorker extends AbstractScheduledWorker {

    private final EntityMapper eventsEntityMapper;

    @Autowired
    public EventsScheduledWorker(@Qualifier("eventsSchedulerHelper") SchedulerHelper eventsSchedulerHelper,
                                 @Qualifier("eventsDb") BTree eventsDb,
                                 @Qualifier("eventsEntityMapper") EntityMapper eventsEntityMapper) {
        super(HdbmDatabasesDescription.EVENTS, eventsDb, eventsSchedulerHelper, Constants.EVENTS_INITIAL_BROWSE_VALUE);
        this.eventsEntityMapper = eventsEntityMapper;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception {

        List<TransactionDomainEvents> result = new LinkedList<>();
        for (String transactionalDomainEventsJson : entitiesJson) {
            Object mappedEntity = eventsEntityMapper.map(transactionalDomainEventsJson);
            Assert.isTrue(mappedEntity.getClass().equals(Constants.EVENTS_MAP_CLASS), "Mapped entity isn't instance of " + Constants.EVENTS_MAP_CLASS);
            TransactionDomainEvents entity = (TransactionDomainEvents) mappedEntity;
            result.add(entity);
        }

        super.schedulerHelper.save(result, dbInfo);
    }

    @Override
    public Object wrapBrowseValue(DatabasesInfo dbInfo) {
        return dbInfo.getBrowseValue() + 1;
    }
}
