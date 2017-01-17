package com.imcode.tools.jdbmtosql.tranfer.interfaces;

import com.imcode.tools.jdbmtosql.enums.DatabaseDescription;

import java.io.IOException;

/**
 * Created by ruslan on 17.01.17.
 */
public interface EntityMapper {

    Object map(String json, DatabaseDescription databaseDescription) throws IOException;

}
