package com.imcode.tools.jdbmtosql.repositories;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.DatabasesDescription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 17.01.17.
 */
public interface DatabasesInfoRepository extends JpaRepository<DatabasesInfo, Long> {

    DatabasesInfo findByDatabasesDescription(DatabasesDescription databasesDescription);

}
