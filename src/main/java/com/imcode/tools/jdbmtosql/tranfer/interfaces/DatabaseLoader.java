package com.imcode.tools.jdbmtosql.tranfer.interfaces;

import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import jdbm.btree.BTree;

/**
 * Created by ruslan on 17.01.17.
 */
public interface DatabaseLoader {

    BTree load(HdbmDatabasesDescription hdbmDatabasesDescription) throws Exception;

}
