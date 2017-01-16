package com.imcode.tools.jdbmtosql;

import com.imcode.tools.jdbmtosql.configurations.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by ruslan on 16.01.17.
 */
public class Application {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    }

}
