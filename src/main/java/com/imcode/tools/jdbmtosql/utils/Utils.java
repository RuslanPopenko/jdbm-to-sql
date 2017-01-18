package com.imcode.tools.jdbmtosql.utils;

import jdbm.btree.BTree;
import jdbm.helper.Tuple;
import jdbm.helper.TupleBrowser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
public class Utils {

    public static List<String> getJsonDatabaseRecords(BTree db, Long timestamp) throws IOException {

        final TupleBrowser browser = db.browse(timestamp);

        Tuple tuple = new Tuple();

        List<String> jsonDatabaseRecords = new LinkedList<>();

        for (int i = 0; (i < Constants.MAX_OBJECTS_NUMBER_FOR_CYCLE) && browser.getNext(tuple); i++) {
            byte[] jsonData = (byte[]) tuple.getValue();
            jsonDatabaseRecords.add(new String(jsonData, Constants.ENCODING));
        }

        return jsonDatabaseRecords;
    }
}
