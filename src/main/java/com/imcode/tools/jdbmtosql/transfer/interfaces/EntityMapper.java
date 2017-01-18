package com.imcode.tools.jdbmtosql.transfer.interfaces;

/**
 * Created by ruslan on 17.01.17.
 */
public interface EntityMapper {

    Object map(String json) throws Exception;

}
