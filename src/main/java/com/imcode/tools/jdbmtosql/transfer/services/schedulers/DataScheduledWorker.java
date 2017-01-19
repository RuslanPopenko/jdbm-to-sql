package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractScheduledWorker;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class DataScheduledWorker extends AbstractScheduledWorker {

    private final EntityMapper dataEntityMapper;
    private final ByteBuffer byteBuffer;

    @Autowired
    public DataScheduledWorker(@Qualifier("dataSchedulerHelper") SchedulerHelper dataSchedulerHelper,
                               @Qualifier("dataDb") BTree dataDb,
                               @Qualifier("dataEntityMapper") EntityMapper dataEntityMapper) {
        super(HdbmDatabasesDescription.DATA, dataDb, dataSchedulerHelper, Constants.DATA_INITIAL_BROWSE_VALUE);
        this.dataEntityMapper = dataEntityMapper;
        this.byteBuffer =  ByteBuffer.allocate(Long.SIZE);
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception {

        for (String entityJson : entitiesJson) {
            Object mappedEntity =  dataEntityMapper.map(entityJson);
            super.schedulerHelper.save(mappedEntity, dbInfo);
        }

    }

    @Override
    public Object wrapBrowseValue(DatabasesInfo dbInfo) {
        return longToBytes(dbInfo.getBrowseValue());
    }

    public byte[] longToBytes(Long val) {
        byteBuffer.putLong(val);
        return byteBuffer.array();
    }
}
