package com.imcode.tools.jdbmtosql.repositories;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 17.01.17.
 */
public interface DatabasesinfoRepository extends JpaRepository<DatabasesInfo, Long> {
}
