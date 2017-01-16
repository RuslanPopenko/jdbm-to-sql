package com.imcode.tools.jdbmtosql.tranfer.services;

import com.imcode.tools.jdbmtosql.tranfer.interfaces.DataReceiver;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.RecordManagerOptions;
import jdbm.btree.BTree;
import jdbm.helper.*;
import jdbm.recman.CacheRecordManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ruslan on 16.01.17.
 */
@Service
public class DataReceiverImpl implements DataReceiver {

    @Resource
    private Environment environment;

    private RecordManager recordManager;
    private BTree index;
    private Serializer serializer;

    public void receive() throws IOException {

        String dataPath = environment.getRequiredProperty("DataPath");
        String eventStorePath = environment.getRequiredProperty("EventStorePath");
        Properties properties = new Properties();

        properties.put(RecordManagerOptions.AUTO_COMMIT, "false");
        properties.put(RecordManagerOptions.DISABLE_TRANSACTIONS, "false");

        recordManager = RecordManagerFactory.createRecordManager(eventStorePath, properties);
        serializer = new ByteArraySerializer();
        recordManager = new CacheRecordManager(recordManager, new MRU(1000));
        long recid = recordManager.getNamedObject("index");

        if (recid != 0)
        {
            index = BTree.load(recordManager, recid);
        } else
        {
            LongComparator comparator = new LongComparator();
            index = BTree.createInstance(recordManager, comparator, new LongSerializer(), serializer, 16);
            recordManager.setNamedObject("index", index.getRecid());
        }



    }
}
