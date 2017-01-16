package com.imcode.tools.jdbmtosql.repositories;

import com.imcode.tools.jdbmtosql.entities.ApplicationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 16.01.17.
 */
public interface ApplicationEventRepository extends JpaRepository<ApplicationEvent, String> {
}
