package com.imcode.tools.jdbmtosql;

import com.imcode.tools.jdbmtosql.configurations.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

/**
 * Created by ruslan on 16.01.17.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    }

}
