package com.imcode.tools.jdbmtosql.tranfer.interfaces;

import com.imcode.tools.jdbmtosql.enums.DatabaseDescription;
import jdbm.btree.BTree;

import java.io.IOException;

/**
 * Created by ruslan on 17.01.17.
 */
public interface DatabaseLoader {

    BTree load(DatabaseDescription databaseDescription) throws IOException;

}
