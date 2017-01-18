package com.imcode.tools.jdbmtosql.tranfer.services.dbload;

import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.DatabaseLoader;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.RecordManagerOptions;
import jdbm.btree.BTree;
import jdbm.helper.*;
import jdbm.recman.CacheRecordManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by ruslan on 17.01.17.
 */
@Service
public class DatabaseLoaderImpl implements DatabaseLoader {

    @Resource
    private Environment environment;

    public BTree load(HdbmDatabasesDescription hdbmDatabasesDescription) throws Exception {

        String eventStorePath = environment.getRequiredProperty(hdbmDatabasesDescription.getPropertyKey());
        Properties properties = new Properties();

        properties.put(RecordManagerOptions.AUTO_COMMIT, "false");
        properties.put(RecordManagerOptions.DISABLE_TRANSACTIONS, "false");

        RecordManager recordManager = RecordManagerFactory.createRecordManager(eventStorePath, properties);
        Serializer serializer = new ByteArraySerializer();
        recordManager = new CacheRecordManager(recordManager, new MRU(Constants.MRU_CACHE_MAX_SIZE));
        long recid = recordManager.getNamedObject(Constants.NAMED_OBJECT_VALUE);

        BTree index;

        if (recid != 0) {
            index = BTree.load(recordManager, recid);
        } else {
            LongComparator comparator = new LongComparator();
            index = BTree.createInstance(recordManager, comparator, new LongSerializer(), serializer, Constants.PAGE_SIZE);
            recordManager.setNamedObject(Constants.NAMED_OBJECT_VALUE, index.getRecid());
        }

        return index;

    }

}
