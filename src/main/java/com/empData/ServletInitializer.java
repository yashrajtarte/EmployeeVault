package com.empData;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Configures the application when it is deployed to a servlet container (e.g., Tomcat).
 * This class is required for WAR deployments and allows Spring Boot to be initialized properly.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configures the Spring application builder to use the main application class.
     *
     * @param application the Spring application builder
     * @return the configured application builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(EmpDataApplication.class);
    }
}
